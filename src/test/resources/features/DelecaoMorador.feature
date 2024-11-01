# language: pt
Funcionalidade: Deletar um Morador
  Como usuário da API
  Quero conseguir deletar um morador
  Para que o registro seja apagado corretamente no sistema

  Contexto: Cadastro bem-sucedido do morador
    Dado que eu tenha os seguintes dados do morador:
      | campo    | valor           |
      | nome     | Emilio          |
      | cpf      | 12312312345     |
      | email    | teste@teste.com |
      | imovelId | 1               |
    Quando eu enviar a requisição para o endpoint "/api/moradores" de cadastro de morador
    Então o status code da resposta para cadastro de morador deve ser 201

  Cenário: Deve ser possível deletar um morador
    Dado que eu recupere o ID do morador criado no contexto
    Quando eu enviar a requisição com o ID para o endpoint "/api/moradores/{id}" de deleção do morador
    Então o status code da resposta para cadastro de morador deve ser 204