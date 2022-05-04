# Drones
## Multimodule SpringBoot microservice to manage drone operations
This is a practical task to Musala Soft


## Configuration
To use the configuration module the only required thing is open a connection with postgres and run the project with application.yml profile, you can find it in boot ms_back/boot/src/main/resources/application.yml. In this file you gonna find the following:

```yml

spring:
  jpa:
    hibernate:
      ddl-auto: create
    defer-datasource-initialization: true

  datasource:
    url: jdbc:postgresql://${SQL_HOST:localhost}:${PORT:5433}/${DBNAME:drone}
    username: username
    password: password
    driver-class-name: org.postgresql.Driver

  sql:
    init:
      mode: always
      data-locations: classpath:data.sql

server:
  port: 8081

```

You have to edit the database_endpoint, port, database_name, username and password with your own data and run to check all functionalities in the MicroService.


In api-rest module you can find a postman collection with operations needed




