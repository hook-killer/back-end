FROM  --platform=linux/amd64 openjdk:17-alpine
ARG JAR_FILE=build/libs/app.jar
ARG ACTIVE_PROFILES=dev
RUN mkdir /pinpoint

COPY ${JAR_FILE} /
COPY pinpoint/* /pinpoint/

USER root
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=${ACTIVE_PROFILES}","-javaagent:/pinpoint/pinpoint-bootstrap-2.5.2.jar","-Dpinpoint.applicationName=be-app","-Dpinpoint.agentId=app-1","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
