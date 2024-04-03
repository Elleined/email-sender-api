# email-sender
API for sending email

# How to send email
 - Goto Google Account 
 - Search App Password
 - In Select App Choose Other(Custom name) and provide name you want
 - Click generate 
 - Copy the 16 letter characters that is your app password
 - Go to application.properties set these following
 ```
 spring.mail.username=juandelacruz@gmail.com  
 spring.mail.password=abcdefghijkl
 app.name=my_app
 ```
 - Check API endpoints in Postman.

# Technologies used
  - Spring boot
  - Spring mvc
  - Java mail API
  - Hibernate validator

# Useful links
 - Generate the app password: https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/

# Run with Docker
1. Docker Run Email Sender API
```
docker run -itd --rm -p 8091:8091 --name esa_app --env-file ./esa.env elleined/esa
```

# Run with Docker Compose
```
docker compose --env-file <env_file_path> up
```

# Check API endpoints in Postman
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/26932885-7fc11acb-7833-4ce0-a5eb-f899f0d44026?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D26932885-7fc11acb-7833-4ce0-a5eb-f899f0d44026%26entityType%3Dcollection%26workspaceId%3D638a4bab-020e-48f2-950a-f85be75bbe0c)
