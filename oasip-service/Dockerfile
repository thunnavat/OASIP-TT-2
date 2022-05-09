FROM maven:3-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
  ["mvn", "-f", "pom.xml", "dependency:go-offline"]
COPY src src
RUN --mount=type=cache,target=/root/.m2 \
  ["mvn", "-f", "pom.xml", "clean", "package", "-DskipTests"]

FROM openjdk:16-jdk-alpine
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]