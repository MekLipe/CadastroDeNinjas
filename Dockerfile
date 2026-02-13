FROM eclipse-temurin:17
LABEL maintainer="Luis Kopper"
WORKDIR /app
COPY target/CadastroDeNinjas-0.0.1-SNAPSHOT.jar /app/aula-docker.jar
ENTRYPOINT ["java", "-jar", "aula-docker.jar"]