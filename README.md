# Description of web application - Advertisement service
This project - **Advertisement service** - is **REST API** application which provides ability to **sell/buy items**

## Features
+ Advertisement Creating/Updating/Deleting/Finding
+ User Creating/Updating/Deleting
+ Authorization by JWT token
+ User chatting about advertisements
+ Saving and loading images

## Future features
+ Possibility to leave review for advertisement
+ Advertisement searching system

## Dependencies
+ Java 17
+ Maven
+ Spring Framework Boot 3.1 (web, security, data-jpa, validation and others)
+ Hibernate 6.1.7
+ MySQL
+ Jwt
+ Log4j
+ Spring-doc
+ Mockito 5.3.1
+ Junit 5.9.3

# Deploying
  
  Deployment by **Docker** consists of ***building*** and ***Containerizing an application***

  > The default port for the application is **8080**, and for connecting to the database - **3306** (to change it look at docker-compose.yml and Dockerfile)

**Building**

  For build application you need to write at project root:
  
    ./mwnw build

**Containerizing an application**

  For containerize application you need to write at project root:
  
    docker compose up
  

