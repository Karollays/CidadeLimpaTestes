package steps;

import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.LoginModel;
import org.junit.Assert;
import services.CadastroCaminhaoService;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CadastroCaminhaoSteps {

    private final CadastroCaminhaoService cadastroCaminhaoService = new CadastroCaminhaoService();
    private String token;

    @Dado("que eu tenha o token de autenticação")
    public void queEuTenhaOTokenDeAutenticação() {
        String loginJson = new Gson().toJson(new LoginModel("email2@email.com.br", "1234567", "ADMIN"));

        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body(loginJson)
                .when()
                .post("http://localhost:8080/auth/login");  // Ajuste a URL se necessário

        loginResponse.then().statusCode(200);  // Garante que o login foi bem-sucedido
        token = loginResponse.jsonPath().getString("token");  // Recupera o token da resposta
        System.out.println("Token: " + token);  // Loga o token para verificação
    }

    @E("os seguintes dados do caminhão:")
    public void osSeguintesDadosDoCaminhão(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroCaminhaoService.setFieldsCaminhao(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de caminhoes")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeCaminhoes(String endPoint) {
        cadastroCaminhaoService.createCaminhao(endPoint, token);  // Envia a requisição com o token
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroCaminhaoService.getResponse().statusCode());  // Valida o status code
    }


    @E("os campos {string} e {string} estão ausentes nos dados do caminhão")
    public void osCamposEEstãoAusentesNosDadosDoCaminhão(String registro, String capacidade) {
        cadastroCaminhaoService.removerCampo(registro);
        cadastroCaminhaoService.removerCampo(capacidade);
    }

    @E("a resposta deve conter uma mensagem indicando que o campo {string} é obrigatório")
    public void aRespostaDeveConterUmaMensagemIndicandoQueOCampoÉObrigatório(String campo) {
        // Define a mensagem esperada com base no campo
        String expectedMessage = campo.toLowerCase() + " é obrigatório";

        // Extrai a mensagem de erro específica para o campo da resposta da API e converte para minúsculas
        String actualMessage = cadastroCaminhaoService.getResponse().jsonPath().getString(campo).toLowerCase();

        // Valida se a mensagem retornada contém a mensagem esperada, ignorando maiúsculas e minúsculas
        Assert.assertNotNull("A mensagem de erro para o campo " + campo + " não foi encontrada.", actualMessage);
        Assert.assertTrue("A mensagem de erro para o campo " + campo + " está incorreta.", actualMessage.contains(expectedMessage));
    }

//    @Dado("que eu tenha um token de autenticação inválido")
//    public void queEuTenhaUmTokenDeAutenticaçãoInválido() {
//        this.token = "token_invalido";
//    }

}
