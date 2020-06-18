FROM openjdk:8-jdk-alpine
COPY target/fleet-management-0.0.1.jar app.jar
COPY ./docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod 755 /docker-entrypoint.sh
RUN touch /app.jar && mkdir -p /config
WORKDIR /
EXPOSE 8080
ENTRYPOINT [ "/docker-entrypoint.sh" ]
CMD ["java","-jar","-Dspring.profiles.active=dev","/app.jar"]