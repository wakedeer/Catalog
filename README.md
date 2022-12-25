# **Product Catalog**

## General

The project is built on Java 11 and Spring Boot Framework

## Structure

Project contains following microservices:

- **auth-uaa** - Authorization service.
- **product-service** - Responsible for aggregating product information
- **product-reviews** - Responsible for product review

## Build and run the Gradle inside the projects folders

- ``gradlew clean assemble`` - build source code
- ``gradlew bootRun`` - run app

**Note**
To start the **product-reviews** microservice you should launch Postgres database
```
cd ./product-reviews
docker-compose up -d
gradlew bootRun
```

## Dockerized project

- ``gradlew bootJar`` - create jar file
- ``docker build -t wakedeer/auth-uaa:0.0.1 .`` - create docker image
- ``docker push wakedeer/auth-uaa:0.0.1`` - publish docker image

## Request for check

1. Obtaining token

```
curl -vi -d grant_type=client_credentials "product-client:27917C58F8435247CF2C5C9D0EDF64AE702C005ACFE242110CC9C4F0E7AF2758@localhost:8081/sso-auth-server/oauth/token"
```

2. Extract jwt-token and use it with request

```
curl --location --request GET 'http://localhost:8082/product/BB5476' \
--header 'Authorization: Bearer <pass jwt token here>'
```
 
Also, we can find **postman** request examples in **postman** folder

## Deploying using docker-compose
You can run all microservices using docker-compose
```
docker-compose up -d
```