# language: pt
Funcionalidade: Validar o contrato ao realizar um cadastro bem-sucedido de entrega usuário
  Quero cadastrar um novo usuário
  Para que eu cosniga validar se o contrato esta conforme o esperado
  Cenario: Validar contrato do cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados de cadastro de usuário:
      | campo   | valor              |
      | id      | 1                  |
      | nome    | Adriano Kim        |
      | email   | email@email.com.br |
      | senha   | 123456             |
      | role    | ADMIN              |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuário
    Então o status code para usuário deve ser 201
    E que o arquivo de contrato esperado é o "Cadastro bem-sucedido de usuário"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado