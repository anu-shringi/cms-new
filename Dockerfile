FROM openjdk:8-jdk
EXPOSE 8080
COPY target/*.jar java-docker.jar
ENTRYPOINT ["java", "-jar", "/java-docker.jar"]
VOLUME /tmp
