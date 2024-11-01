# language: pt
Funcionalidade: Cadastro de novo caminhao
  Como usuário da API
  Quero cadastrar um novo caminhao
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de caminhao
    Dado que eu tenha o token de autenticação
    E os seguintes dados do caminhão:
      | campo          | valor        |
      | registro       | CL02006666   |
      | capacidade     | 356.0        |
    Quando eu enviar a requisição para o endpoint "/api/caminhoes" de cadastro de caminhoes
    Então o status code da resposta deve ser 201

  Cenário: Falha de cadastro por campos obrigatórios ausentes
    Dado que eu tenha o token de autenticação
    E os campos "capacidade" e "registro" estão ausentes nos dados do caminhão
    Quando eu enviar a requisição para o endpoint "/api/caminhoes" de cadastro de caminhoes
    Então o status code da resposta deve ser 400
    E a resposta deve conter uma mensagem indicando que o campo "capacidade" é obrigatório
    E a resposta deve conter uma mensagem indicando que o campo "registro" é obrigatório

#  Cenário: Falha de cadastro por token de autenticação inválido
#    Dado que eu tenha um token de autenticação inválido
#    E os seguintes dados do caminhão:
#      | campo      | valor        |
#      | registro   | CL02006666   |
#      | capacidade | 356.0        |
#    Quando eu enviar a requisição para o endpoint "/api/caminhoes" de cadastro de caminhoes
#    Então o status code da resposta deve ser 401
#    E a resposta deve conter uma mensagem indicando que o token é inválido ou expirado

#  Cenário: Cadastro de caminhão com dados duplicados
#    Dado que eu tenha o token de autenticação
#    E os seguintes dados do caminhão:
#      | campo      | valor        |
#      | registro   | CL02006666   |
#      | capacidade | 356.0        |
#    Quando eu enviar a requisição para o endpoint "/api/caminhoes" de cadastro de caminhoes
#    E o caminhão já estiver cadastrado no sistema
#    Então o status code da resposta deve ser 409
#    E a resposta deve conter uma mensagem indicando que o caminhão já está cadastrado
