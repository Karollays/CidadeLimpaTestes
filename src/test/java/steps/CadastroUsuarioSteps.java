package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CadastroUsuarioSteps {

    CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService();
    private String message;

    @Dado("que eu tenha os seguintes dados de cadastro de usuário:")
    public void queEuTenhaOsSeguintesDadosDeCadastroDeUsuário(List<Map<String, String>> rows) {

        for (Map<String, String> columns : rows) {
            cadastroUsuarioService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
        }

    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuário")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuário(String endPoint) {
        cadastroUsuarioService.createUser(endPoint);

    }

    @Então("o status code para usuário deve ser {int}")
    public void oStatusCodeParaUsuárioDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroUsuarioService.response.statusCode());
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException {
        cadastroUsuarioService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException {
        Set<ValidationMessage> validateResponse = cadastroUsuarioService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @E("a api deve retornar a mensagem {string}")
    public void aApiDeveRetornarAMensagem(String message) {
        Assert.assertEquals(400, cadastroUsuarioService.response.statusCode());
        String responseMessage = cadastroUsuarioService.response.jsonPath().getString("senha");
        System.out.println("Mensagem da resposta: " + responseMessage);
        Assert.assertEquals(message, responseMessage);
    }

}

