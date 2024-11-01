# language: pt
Funcionalidade: Cadastro de novo morador
  Como usuário da API
  Quero cadastrar um novo morador
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de morador
    Dado que eu tenha os seguintes dados do morador:
      | campo    | valor              |
      | nome     | Emilio             |
      | cpf      | 12345678991        |
      | email    | emilio@example.com |
      | imovelId | 1                  |
    Quando eu enviar a requisição para o endpoint "/api/moradores" de cadastro de morador
    Então o status code da resposta para cadastro de morador deve ser 201

  Cenário: Cadastro de morador sem sucesso ao passar o campo cpf inválido
    Dado que eu tenha os seguintes dados do morador:
      | campo    | valor              |
      | nome     | Emilio             |
      | cpf      | 123456             |
      | email    | emilio@example.com |
      | imovelId | 1                  |
    Quando eu enviar a requisição para o endpoint "/api/moradores" de cadastro de morador
    Então o status code da resposta para cadastro de morador deve ser 400
    E o corpo de resposta de erro da api deve retornar a mensagem "O campo deve conter 11 caracteres"
