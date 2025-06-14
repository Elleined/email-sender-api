FROM eclipse-temurin:21-jdk-alpine
RUN apk add --no-cache tzdata && \
    ln -snf /usr/share/zoneinfo/Asia/Manila /etc/localtime && \
    echo "Asia/Manila" > /etc/timezone
WORKDIR /app
COPY build/libs/email-sender-api.jar ./email-sender-api.jar
ENTRYPOINT ["java", "-jar", "email-sender-api.jar"]