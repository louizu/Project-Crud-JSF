FROM maven:4.0.0-openjdk-21 AS builder

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app
COPY . /app/
RUN mvn clean package

#
FROM openjdk:21-alpine
# Définissez le répertoire de travail dans le conteneur
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
