# README

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fhook-killer%2Fback-end&count_bg=%2379C83D&title_bg=%23E90D0D&icon=&icon_color=%23E7E7E7&title=%EB%B0%B1%EC%97%94%EB%93%9C+%EC%9D%91%EC%95%A0&edge_flat=false)](https://hits.seeyoufarm.com)

## Index

- [README](#readme)
  - [Index](#index)
  - [1️⃣Tech Stack](#1️⃣tech-stack)
    - [Application](#application)
    - [Service](#service)
    - [CI / CD](#ci--cd)
    - [ETC](#etc)
  - [2️⃣ERD](#2️⃣erd)
  - [3️⃣Endpoint 정리](#3️⃣endpoint-정리)
  - [4️⃣실행 방법](#4️⃣실행-방법)
    - [주의사항](#주의사항)
    - [필수 Plugin, 준비사항](#필수-plugin-준비사항)
    - [Annotation Processor](#annotation-processor)
    - [필요 VMOptions](#필요-vmoptions)
    - [Run](#run)

## 1️⃣Tech Stack

### Application

![JDK17](https://img.shields.io/badge/Java-v.17-CC0000?style=flat&logo=OpenJDK&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-v.8-02303A?style=flat&logo=Gradle&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring-Boot_v.3-6DB33F?style=flat&logo=Spring-Boot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring-Data_JPA-6DB33F?style=flat&logo=Spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring-Security-6DB33F?style=flat&logo=Spring-Security&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring-Cloud-E50914?style=flat&logo=Netflix&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-v.3-005F0F?style=flat&logo=Thymeleaf&logoColor=white)
![jwt](https://img.shields.io/badge/JWT-000000?style=flat&logo=jsonwebtokens&logoColor=white)

### Service

![NCP](https://img.shields.io/badge/NCP-Load_Balancer-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/NCP-Container_Registry-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/NCP-Global_DNS-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/NCP-Object_Storage-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/NCP-Server-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/NCP-NAT_Gateway-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/NCP-Papago_API-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/NCP-Effective_Log_Search_&_Analytics-03C75A?style=flat&logo=Naver&logoColor=white)
![NCP](https://img.shields.io/badge/Naver-Docker_Pinpoint_v2.5.2-03C75A?style=flat&logo=Naver&logoColor=white)
![Kakao](https://img.shields.io/badge/Kakao-OAuth-FFCD00?style=flat&logo=KakaoTalk&logoColor=white)
![Google](https://img.shields.io/badge/Google-OAuth-4285F4?style=flat&logo=Google&logoColor=white)

### CI / CD

![Github Actions](https://img.shields.io/badge/Github_Actions-2088FF?style=flat&logo=Github-Actions&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=Jenkins&logoColor=white)

### ETC

![Github](https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white)
![Intellij IDEA](https://img.shields.io/badge/IntelliJ-000000?style=flat&logo=IntelliJ-IDEA&logoColor=white)
![VSCode](https://img.shields.io/badge/VSCode-007ACC?style=flat&logo=Visual-Studio-Code&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=flat&logo=Slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white)
![PostMan](https://img.shields.io/badge/Postman-FF6C37?style=flat&logo=Postman&logoColor=white)

## 2️⃣ERD

[![ERD](https://github.com/hook-killer/document/blob/main/ERD/ERD_v231027.png?raw=true)](https://dbdocs.io/donsonioc2010/Hook_killer)

## 3️⃣Endpoint 정리

> [!NOTE]  
> 내용이 많아 위키로 기록을 하였습니다.
> 위키 링크만 참조하는점 양해 부탁드립니다.

- [Endpoint 링크 바로가기](https://github.com/hook-killer/back-end/wiki/EndPoint-%EB%A6%AC%EC%8A%A4%ED%8A%B8)

## 4️⃣실행 방법

### 주의사항

> [!WARNING]
> 해당 방법은 Mac을 기반하여 Intellij를 기준으로 설명이 되어있습니다.  
> 또한 문서의 설명은 [Intellij 설정방법](<https://github.com/hook-killer/document/blob/main/Tool/BE-%EC%84%A4%EC%A0%95%EB%B0%A9%EB%B2%95(Intellij).md>)를 토대로 제작되었습니다.

### 필수 Plugin, 준비사항

- LomBok
- OpenJDK 17

### Annotation Processor

1. Mac을 기준으로 `Intellij IDEA` → `Settings`클릭
2. `Build, Execution, Deployment` → `CompilerAnnotation Processors`
3. `Enable annotation processing` 체크표시

### 필요 VMOptions

> [!WARNING]  
> 6개의 항목 모두 비밀키로, 현재는 사용이 불가능합니다.  
> 구글 계정을 사용하실 경우, 이메일과 앱 비밀번호를,  
> Storage Object의 경우 NCP의 Object Strage Key를,  
> papago의 API키는 NCP에서 발급받아 입력하시기 바랍니다.

```java
-Dnaver.storage.access-key=yXOrGQFmk7BfURVyU5Dw
-Dnaver.storage.secret-key=bv0q1CSqr8kPEjZmyTA5qSreGz4FWUTJtJFObiO6
-Dspring.mail.username=${자신의 구글 계정을 기록하세요}
-Dspring.mail.password=${자신의 구글 앱 패스워드를 기록하세요}
-Dpapago.header.X-NCP-APIGW-API-KEY-ID=ybakk0x770
-Dpapago.header.X-NCP-APIGW-API-KEY=DQxOkhtAKEqN61A5vGrp5mYW1j66Qi7vR0R7fJh5
```

### Run

- `HookKiller.server`위치의 `ServerApplication.java`의 Main메소드를 Run하시면 됩니다.
