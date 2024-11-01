package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.MoradorModel;

import static io.restassured.RestAssured.given;

public class CadastroMoradorService {

    private final MoradorModel moradorModel = new MoradorModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private Response response;
    private final String baseUrl = "http://localhost:8080";
    private String idMorador;
    private String token;

    // Método para autenticação e obtenção do token
    public void authenticate(String authEndpoint, String username, String password) {
        String authUrl = baseUrl + authEndpoint;
        response = given()
                .contentType(ContentType.JSON)
                .body("{ \"email\": \"" + username + "\", \"senha\": \"" + password + "\" }")
                .when()
                .post(authUrl)
                .then()
                .extract()
                .response();
        token = response.jsonPath().getString("token");
    }

    public String getToken() {
        return token;
    }

    // Método para criar um novo Morador
    public void createMorador(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(moradorModel);

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getToken())
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    // Método para deletar um Morador usando o ID
    public void deleteMorador(String endPoint) {
        response = given()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .delete(baseUrl + endPoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    // Configura os campos do Morador dinamicamente
    public void setFieldMorador(String field, String value) {
        switch (field) {
            case "nome" -> moradorModel.setNome(value);
            case "cpf" -> moradorModel.setCpf(value);
            case "email" -> moradorModel.setEmail(value);
            case "imovelId" -> moradorModel.setImovelId(Long.parseLong(value));
            default -> throw new IllegalArgumentException("Unexpected field: " + field);
        }
    }

    // Recupera o ID do Morador após criação
    public void retrieveIdMorador() {
        if (response != null && response.getStatusCode() == 201) {
            idMorador = response.jsonPath().getString("id");
        } else {
            throw new IllegalStateException("Unable to retrieve Morador ID.");
        }
    }

    public String getIdMorador() {
        return idMorador;
    }

    // Retorna a última resposta da requisição
    public Response getResponse() {
        return response;
    }

    // Define a última resposta da requisição (para fins de teste e controle)
    public void setResponse(Response response) {
        this.response = response;
    }
}
