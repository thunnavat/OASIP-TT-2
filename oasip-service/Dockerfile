FROM maven:3-openjdk-16 AS build
WORKDIR /app
COPY pom.xml .
RUN ["mvn", "dependency:go-offline"]
COPY src src
RUN ["mvn", "package", "-DskipTests"]

FROM openjdk:16-jdk-alpine
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
CMD ["java", "-jar", "/app.jar"]