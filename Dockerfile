FROM maven:3.8.6-openjdk-18 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18-jdk-slim-buster
COPY --from=build /target/e-commerce-0.0.1-SNAPSHOT.jar e-commerce.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","e-commerce.jar"]