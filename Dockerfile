FROM maven:3.8.4-openjdk-17-slim AS MAVEN_TOOL_CHAIN
COPY .  /chatty
WORKDIR /chatty/chat-service
RUN mvn clean package -DskipTests -Pdocker

FROM openjdk:17-jdk-slim
COPY --from=MAVEN_TOOL_CHAIN /chatty/chat-service/target/chat-service-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "app.jar"]