# 🚛 Projeto de Teste: API Cidade Limpa

Este projeto é um ambiente de teste completo para a API Cidade Limpa, incluindo todos os arquivos necessários para configurar e rodar a aplicação e seus testes automatizados. Este `README.md` fornece instruções sobre como configurar, executar e limpar o ambiente de teste.

---

## 🧾 Conteúdo

- `Dockerfile`: Define o ambiente para a aplicação.
- `docker-compose.yml`: Configura o ambiente com dependências (ex.: Banco de Dados Oracle).
- `README.md`: Instruções detalhadas de execução.

---

## 🔧 Pré-requisitos

1. **Docker** - [Instalar Docker](https://docs.docker.com/get-docker/)
2. **Docker Compose** - [Instalar Docker Compose](https://docs.docker.com/compose/install/)
3. **Java 17** - [Instalar Java](https://adoptopenjdk.net/)
4. **Maven** - [Instalar Maven](https://maven.apache.org/)

---

## 🌍 Configurações de Ambiente

Este projeto utiliza um arquivo `.env` para configurar variáveis de ambiente, incluindo detalhes de conexão ao banco de dados e credenciais. Edite o arquivo `.env` com suas próprias configurações antes de iniciar o projeto.

Exemplo de `.env`:

```properties
# Configurações de Conexão do Banco de Dados Oracle
DATABASE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
DATABASE_USER=SEU_USUARIO
DATABASE_PASSWORD=SUA_SENHA

# Configurações de Inicialização do Flyway
FLYWAY_LOCATIONS=filesystem:./db/migration
FLYWAY_BASELINE_ON_MIGRATE=true
```

---

## 🚀 Configuração e Execução

### Passo 1: Configuração do Ambiente

1. **Clone ou extraia o projeto**:
   ```bash
   unzip ProjetoTesteCidadeLimpa.zip
   cd ProjetoTesteCidadeLimpa
   ```

2. **Configuração de Ambiente**:
   - Certifique-se de que o arquivo `.env` contém as credenciais corretas do banco de dados Oracle e outras configurações.

### Passo 2: Construção do Ambiente

1. **Iniciar Containers**:
   Execute o `docker-compose` para configurar o ambiente:
   ```bash
   docker-compose up -d
   ```
   Isso irá configurar o banco de dados Oracle e iniciar a API no Docker.

2. **Construir a Aplicação**:
   Dentro do container, execute:
   ```bash
   docker exec -it cidade-limpa-api bash -c "mvn install"
   ```

### Passo 3: Executar os Testes

1. **Configuração Inicial**:
   Execute o script de configuração para configurar os dados de teste e permissões.
   ```bash
   ./scripts/setup.sh
   ```

2. **Executar Testes**:
   Execute o seguinte comando para rodar os testes:
   ```bash
   docker exec -it cidade-limpa-api bash -c "mvn test"
   ```

   O Maven executará os testes automatizados e exibirá o resultado no console.

### Passo 4: Limpeza do Ambiente

1. **Finalizar Containers**:
   ```bash
   docker-compose down
   ```
---

## 📄 Estrutura do Projeto

- **src/**: Código-fonte principal da API Cidade Limpa.
- **test/**: Testes automatizados com cenários de sucesso e falha.
- **resources/**: Arquivos de configuração, como scripts de migração do banco de dados.
- **scripts/**: Scripts auxiliares para configuração e limpeza.

---

## 📜 Exemplos de Testes Incluídos

1. **Cadastro de Caminhão**:
   - **Cenário de Sucesso**: Cadastro de caminhão com dados válidos. (validação status code)
   - **Cenário de Falha**: Falha no cadastro devido a campos obrigatórios ausentes. (validação do corpo de resposta JSON)

2. **Cadastro de Morador**:
   - **Cenário de Sucesso**: Tentativa de cadastro de um morador cadastrado. (validação status code)
   - **Cenário de Falha**: Cadastro de morador sem sucesso ao passar CPF inválido (validação do corpo de resposta JSON)

3. **Deleção de Morador**:
   - **Cenário de Sucesso**: Deve ser possíevel deletar o morador (validação do corpo de resposta JSON utilizando contexto)

4. **Cadastro de Usuário**:
   - **Cenário de Sucesso**: Deve ser possíevel criar com sucesso um usuário (validação status code)
   - **Cenário de Falha**: Cadastro de usuário sem sucesso ao passar Senha em formato inválido (validação do corpo de resposta JSON)
     
5. **Contrato de validação de usuários**:    
   - **Cenário de Sucesso**: Validar contrato de cadastro bem-sucedido de usuário (Testes de contrato utilizando JSON Schema)
---

---

## ⚙️ Comandos Úteis

- **Acessar o container da API**:
  ```bash
  docker exec -it cidade-limpa-api bash
  ```

- **Visualizar logs da aplicação**:
  ```bash
  docker logs cidade-limpa-api
  ```

---

## 🛩️ Suporte

Para qualquer problema ou dúvida, sinta-se à vontade para entrar em contato.

---

### 🎉 Contribuições

Contribuições são bem-vindas! Siga o fluxo de _pull requests_ para sugestões de melhoria.

