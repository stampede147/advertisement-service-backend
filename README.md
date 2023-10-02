# Description of web application - Advertisement service
This project - **Advertisement service** - is **REST API** application which provides ability to **sell/buy items**

# Features
+ Advertisements
  + Creating (only for authorized user)
  + Deleting (only for authorized user)
  + Updating (only for authorized user)
  + Getting
    
+ User
  + Creating 
  + Deleting (only for authorized user)
  + Updating (only for authorized user)
  + Getting
    
+ Image
  + Creating (only for authorized user)
  + Getting
    
+ User Authentication
  + JWT (by passing login and password)
    
+ Chat
  + Creating (only for authorized user)
  + Deleting (only for authorized user)
  + Getting (only for authorized user)
    
+ Chat Messaging
  + Creating (only for authorized user)
  + Deleting (only for authorized user)
  + Updating (only for authorized user)
  + Getting (only for authorized user)
    
+ Advertisement-edit (used to generate a layout in frontend, particularly in creating, updating the advertisement )
  + Getting navigation
  + Getting generated layout
 
# Dependencies
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

# Docker deploying

> The default port for the database is 3306. To change it look at docker-compose.yml

> The default port for the application is 8080 and 3306 for database connecting

For deploying using docker you need to
+ install docker
+ start command line from project location
+ run: "docker compose up"
