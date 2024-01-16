## JWT 토큰 구현
- 현재는 기본적인 방식으로 구현하였고 점진적으로 refresh token과 redis를 사용하여 STATEFUL하게 구현할 예정이다.

## 프로젝트를 받아서 application.yml을 생성하고 아래내용을 보면서 작성한다.
```
spring:
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://127.0.0.1:3306/springjwt
    username: {아이디}
    password: {비밀번호}

  jpa:
    hibernate:
      ddl-auto: update
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
  jwt:
    secret: {시크릿키 최대한 길고 복잡하게 등록}
    allowed-origins:
      {허용할 URL1},
      {허용할 URL2}
    allowed-methods:
      GET,
      POST
# 허용할 URL 예시 : https://www.example.com
```
