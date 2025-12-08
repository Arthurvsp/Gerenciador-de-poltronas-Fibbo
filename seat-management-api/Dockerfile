# Stage 1: Build (usa imagem com Java e Maven)
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

# Diretório de trabalho
WORKDIR /app

# Copia pom.xml e baixa dependências (caching layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e builda o JAR
COPY src ./src
RUN mvn package -DskipTests -B

# Stage 2: Runtime (imagem leve só com Java)
FROM eclipse-temurin:17-jre-alpine

# Diretório de trabalho
WORKDIR /app

# Copia o JAR do stage de build
COPY --from=build /app/target/seat-management-api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta
EXPOSE 8080

# Comando para rodar
CMD ["java", "-jar", "app.jar"]