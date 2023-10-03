# Description of web application - Advertisement service
This project - **Advertisement service** - is **REST API** application which provides ability to **sell/buy items**

# Features
+ Creating/Updating/Deleting/Finding advertisements (finding by name and category)
+ User registration/login and authentication by JWT token
+ User chatting about advertisements
+ Saving and loading images
+ Advertisement-edit entity for conventient displaying advertisement details for creating and editting (used in frontend)

# Future features
+ Possibility to leave review for advertisement
 
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
