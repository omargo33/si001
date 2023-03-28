### Docker file for building the image for Spring Boot application
### Build the image using the command: docker build -t spring-boot-app .
###
### docker build -t myorg/myapp .
###
### docker run -p 8080:8080 --name LG00100 myorg/myapp
###
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]