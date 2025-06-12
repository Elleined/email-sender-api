# email-sender
API for sending email

# Generate App Password 
[Generate app password tutorial](https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/)
1. Goto Google Account 
2. Search App Password
3. In Select App Choose Other(Custom name) and provide name you want
4. Click create 
5. Copy the 16 letter characters that is your app password

# How to run
## dev
1. Supply the proper environment variables in deployment/dev/.env
2. Add that .env to IDE environment variables (Prefer using IntelliJ)
3. Run the local project for development

## prod
1. Supply the proper environment variables in deployment/prod/.env
2. CD to deployment/prod
3. Run the production project
```
docker compose up -d
```

# Check API endpoints in Postman
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/26932885-7fc11acb-7833-4ce0-a5eb-f899f0d44026?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D26932885-7fc11acb-7833-4ce0-a5eb-f899f0d44026%26entityType%3Dcollection%26workspaceId%3D638a4bab-020e-48f2-950a-f85be75bbe0c)
