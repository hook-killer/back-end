FROM  --platform=linux/amd64 openjdk:17-alpine
ARG JAR_FILE=build/libs/app.jar

RUN mkdir /pinpoint

COPY ${JAR_FILE} /
COPY pinpoint/ /pinpoint/


ARG SPRING_PROFILE
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

ENV SPRING_PROFILE=${SPRING_PROFILE}
ENV PAPAGO_ID=${PAPAGO_ID}
ENV PAPAGO_KEY=${PAPAGO_KEY}
ENV MAIL_ID=${MAIL_ID}
ENV MAIL_PW=${MAIL_PW}
ENV NAVER_ACCESS_KEY=${NAVER_ACCESS_KEY}
ENV NAVER_SECRET_KEY=${NAVER_SECRET_KEY}
ENV KAKAO_CLIENT_ID=${KAKAO_CLIENT_ID}
ENV KAKAO_CLIENT_SECRET=${KAKAO_CLIENT_SECRET}
ENV KAKAO_APP_ID=${KAKAO_APP_ID}
ENV KAKAO_ADMIN_KEY=${KAKAO_ADMIN_KEY}
ENV HOST_NAME=$(hostname)


USER root
EXPOSE 8080
ENTRYPOINT ["sh", "-c","java -Dspring.profiles.active=$SPRING_PROFILE -Djava.security.egd=file:/dev/./urandom -Dpapago.header.X-NCP-APIGW-API-KEY-ID=$PAPAGO_ID -Dpapago.header.X-NCP-APIGW-API-KEY=$PAPAGO_KEY -Dspring.mail.username=$MAIL_ID -Dspring.mail.password=$MAIL_PW -Dnaver.storage.access-key=$NAVER_ACCESS_KEY -Dnaver.storage.secret-key=$NAVER_SECRET_KEY -Doauth2.kakao.client-id=$KAKAO_CLIENT_ID -Doauth2.kakao.client-secret=$KAKAO_CLIENT_SECRET -Doauth2.kakao.app-id=$KAKAO_APP_ID -Doauth2.kakao.admin-key=$KAKAO_ADMIN_KEY -javaagent:/pinpoint/pinpoint-bootstrap-2.5.2.jar -Dpinpoint.applicationName=be-app -Dpinpoint.agentId=$HOST_NAME -jar /app.jar"]
