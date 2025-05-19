FROM gradle:8.14.0-jdk21-alpine AS build
WORKDIR /app
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN ./gradlew clean build --no-daemon

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/email-sender-api.jar .
ENTRYPOINT ["java", "-jar", "email-sender-api.jar"]