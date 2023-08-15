# email-sender
This repository contains the template for sending email with attachment, sending simple email, and sending OTP in email with expiration

# How to send email
 - First make sure to run this [discovery-server-service](https://github.com/Elleined/eureka-discovery-service)
 - Generate app password first check the provided link below.
 - The spring.mail.username property will be your email youre going to use to generate the app password.
 - The spring.mail.password property will be your 16 digit generated password.
 - Check API endpoints in Postman.

# Technologies used
  - Spring boot
  - Spring mvc
  - Java mail API
  - Hbernate validator

# Usefull links
 - Generate the app password: https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/

# Check API endpoints in Postman
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/26932885-7fc11acb-7833-4ce0-a5eb-f899f0d44026?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D26932885-7fc11acb-7833-4ce0-a5eb-f899f0d44026%26entityType%3Dcollection%26workspaceId%3D638a4bab-020e-48f2-950a-f85be75bbe0c)
