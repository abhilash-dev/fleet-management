FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY .fleet-management/target/fleet-management-0.0.1.jar /app.jar
RUN touch /app.jar
WORKDIR /
EXPOSE 8080
CMD [ "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]