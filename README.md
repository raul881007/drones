# Drones
## Multimodule SpringBoot microservice to manage drone operations
This is a practical task to Musala Soft


## Configuration
To use the configuration module the only required thing is open a connection with postgres and run the project with application.yml profile, you can find it in boot ms_back/boot/src/main/resources/application.yml. In this file you gonna find the following:

```yml

spring:
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
        show-sql: false

  datasource:
    url: jdbc:postgresql://${database_endpoint:port}/${database_name}
    username: ${username}
    password: ${password}
    driver-class-name: org.postgresql.Driver

```

You have to edit the database_endpoint, port, database_name, username and password with your own data and run in database ms_back/boot/src/main/resources/data.sql to check all functionalities in the MicroService.


In api-rest module you can find a postman collection with operations needed




