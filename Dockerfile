FROM openjdk:17-oracle

COPY ./target/adservice-project-1.0-SNAPSHOT.jar adservice.jar

ENV DB_HOST=database
ENV DB_PORT=3306
ENV DB_NAME=services

ENTRYPOINT ["java", "-jar", "/adservice.jar"]

EXPOSE 8080
