FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "app.jar"]
#FROM maven:3.5-jdk-8