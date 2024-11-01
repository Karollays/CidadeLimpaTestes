package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.CadastroMoradorService;
import io.cucumber.java.Before;

import java.util.List;
import java.util.Map;

public class CadastroMoradorSteps {
    CadastroMoradorService cadastroMoradorService = new CadastroMoradorService();
    Long moradorId;
    String message;
    @Before
    public void authenticate() {
        cadastroMoradorService.authenticate("/auth/login", "email2@email.com.br", "1234567");
    }

    @Dado("que eu tenha os seguintes dados do morador:")
    public void queEuTenhaOsSeguintesDadosDoMorador(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroMoradorService.setFieldMorador(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de morador")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeMorador(String endPoint) {
        cadastroMoradorService.createMorador(endPoint);
    }

    @Então("o status code da resposta para cadastro de morador deve ser {int}")
    public void oStatusCodeDaRespostaParaCadastroMoradorDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroMoradorService.getResponse().statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        Assert.assertEquals(400, cadastroMoradorService.getResponse().statusCode());
        String responseMessage = cadastroMoradorService.getResponse().jsonPath().getString("cpf");
        System.out.println("Mensagem da resposta: " + responseMessage);
        Assert.assertEquals(message, responseMessage);
    }


    @Dado("que eu recupere o ID do morador criado no contexto")
    public void queEuRecupereOIDDoMoradorCriadoNoContexto() {
        moradorId = cadastroMoradorService.getResponse().jsonPath().getLong("id");
    }

    @Quando("eu enviar a requisição com o ID para o endpoint {string} de deleção do morador")
    public void euEnviarARequisiçãoComOIDParaOEndpointDeDeleçãoDoMorador(String endPoint) {
        String endpointComId = endPoint.replace("{id}", String.valueOf(moradorId));
        cadastroMoradorService.deleteMorador(endpointComId);
    }
}

