FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/email-sender-api.jar ./email-sender-api.jar
ENTRYPOINT ["java", "-jar", "email-sender-api.jar"]