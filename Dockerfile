FROM openjdk:8-jdk-alpine
COPY target/fleet-management-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","/app.jar"]