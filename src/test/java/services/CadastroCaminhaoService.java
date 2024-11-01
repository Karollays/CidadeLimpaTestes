package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CaminhaoModel;

import static io.restassured.RestAssured.given;

public class CadastroCaminhaoService {

    private final CaminhaoModel caminhaoModel = new CaminhaoModel();
    private final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private String baseUrl = "http://localhost:8080";  // Ajuste a URL se necessário
    private Response response;

    /**
     * Configura os campos do caminhão com base no nome e valor fornecidos.
     */
    public void setFieldsCaminhao(String field, String value) {
        switch (field) {
            case "numeroCaminhao" -> caminhaoModel.setNumeroCaminhao(Integer.parseInt(value));
            case "registro" -> caminhaoModel.setRegistro(value);
            case "capacidade" -> caminhaoModel.setCapacidade(value);
            default -> throw new IllegalStateException("Campo inesperado: " + field);
        }
    }


    public void removerCampo(String campo) {
        switch (campo) {
            //case "numeroCaminhao" -> caminhaoModel.setNumeroCaminhao(null);
            case "registro" -> caminhaoModel.setRegistro(null);
            case "capacidade" -> caminhaoModel.setCapacidade(null);
            default -> throw new IllegalStateException("Campo inesperado: " + campo);
        }
    }

    public void createCaminhao(String endPoint, String token) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(caminhaoModel);

        response = given()
                .header("Authorization", "Bearer " + token)  // Certifique-se de que o token é válido
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .log().all()  // Loga a resposta para depuração
                .extract()
                .response();

    }

    /**
     * Retorna a resposta da requisição.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Define a resposta da requisição.
     */
    public void setResponse(Response response) {
        this.response = response;
    }
}
