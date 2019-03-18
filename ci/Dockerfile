FROM openjdk:8-jre-alpine
WORKDIR /opt/sign_on_server
EXPOSE 8080

# TODO don't run as root user
COPY build/libs/sign_on_server-0.0.1-SNAPSHOT.jar sign_on_server.jar
CMD [ "java", "-jar", "sign_on_server.jar" ]
