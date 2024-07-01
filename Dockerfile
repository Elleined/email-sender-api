FROM openjdk:17-alpine
MAINTAINER Elleined

ENV PORT=8091

ADD ./target/*.jar email-sender-api.jar
EXPOSE 8091
CMD ["java", "-jar", "email-sender-api.jar"]
