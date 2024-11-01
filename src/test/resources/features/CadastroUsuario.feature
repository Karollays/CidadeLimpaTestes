# language: pt
Funcionalidade: Cadastro de novo usuário
  Como usuário da API
  Quero me cadastrar
  Para que eu possa me cadastrar no sistema
  Cenário: Cadastro de usuário bem-sucedido
    Dado que eu tenha os seguintes dados de cadastro de usuário:
      | campo   | valor              |
      | id      | 1                  |
      | nome    | Adriano Kim        |
      | email   | email@email.com.br |
      | senha   | 123456             |
      | role    | ADMIN              |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuário
    Então o status code para usuário deve ser 201

  Cenário: Cadastro de usuário sem sucesso ao passar a senha em formato inválido
    Dado que eu tenha os seguintes dados de cadastro de usuário:
      | campo   | valor              |
      | id      | 1                  |
      | nome    | Adriano Kim        |
      | email   | email@email.com.br |
      | senha   | 123                |
      | role    | ADMIN              |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuário
    Então o status code para usuário deve ser 400
    E a api deve retornar a mensagem "A senha deve conter de 6 a 20 caracteres"