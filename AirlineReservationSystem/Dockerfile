FROM openjdk:14-jdk-alpine
ADD /target/dockerars.jar dockerars.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dockerars.jar"]