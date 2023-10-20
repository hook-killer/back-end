FROM  --platform=linux/amd64 openjdk:17-alpine
ARG JAR_FILE=build/libs/app.jar

RUN mkdir /pinpoint

COPY ${JAR_FILE} /
COPY pinpoint/ /pinpoint/


ARG PROFILE
ARG PAPAGO_ID
ARG PAPAGO_KEY
ARG MAIL_ID
ARG MAIL_PW
ARG NAVER_ACCESS_KEY
ARG NAVER_SECRET_KEY
ARG KAKAO_CLIENT_ID
ARG KAKAO_CLIENT_SECRET
ARG KAKAO_APP_ID
ARG KAKAO_ADMIN_KEY
ENV HOST_NAME=$(hostname)


USER root
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-Djava.security.egd=file:/dev/./urandom", "-Dpapago.header.X-NCP-APIGW-API-KEY-ID=${PAPAGO_ID}", "-Dpapago.header.X-NCP-APIGW-API-KEY=${PAPAGO_KEY}", "-Dspring.mail.username=${MAIL_ID}", "-Dspring.mail.password=${MAIL_PW}", "-Dnaver.storage.access-key=${NAVER_ACCESS_KEY}", "-Dnaver.storage.secret-key=${NAVER_SECRET_KEY}", "-Doauth2.kakao.client-id=${KAKAO_CLIENT_ID}", "-Doauth2.kakao.client-secret=${KAKAO_CLIENT_SECRET}", "-Doauth2.kakao.app-id=${KAKAO_APP_ID}", "-Doauth2.kakao.admin-key=${KAKAO_ADMIN_KEY}", "-javaagent:/pinpoint/pinpoint-bootstrap-2.5.2.jar", "-Dpinpoint.applicationName=be-app", "-Dpinpoint.agentId=${HOST_NAME}", "-jar", "/app.jar" ]
