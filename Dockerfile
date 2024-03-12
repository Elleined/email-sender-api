FROM openjdk:17-alpine
MAINTAINER Elleined

ENV MAIL_USERNAME=juandelacruz@gmail.com
ENV MAIL_PASSWORD=abcdefghijklm

ADD ./target/*.jar email-sender-api.jar
EXPOSE 8091
CMD ["java", "-jar", "email-sender-api.jar"]
