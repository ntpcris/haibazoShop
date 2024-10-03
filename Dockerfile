
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/main-0.0.1-SNAPSHOT.jar main.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "main.jar"]
