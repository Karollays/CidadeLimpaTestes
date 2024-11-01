# Etapa 1: Usar Maven com JDK 17 para construir o projeto de testes
FROM maven:3.8.8-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar o arquivo pom.xml e o diretório src para dentro do container
COPY pom.xml .
COPY src ./src

# Compilar o projeto e criar o arquivo JAR sem rodar os testes
RUN mvn clean package -DskipTests

# Etapa 2: Usar JDK 17 leve para rodar a aplicação
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar o JAR gerado da fase de build para o container final
COPY --from=build /app/target/CidadeLimpaTests-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
