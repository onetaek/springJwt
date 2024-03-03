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

## 참고

- 로그인 요청을 Controller 단에서 받는 것보다 Filter를 통해 처리하는데 이를 통해 Controller 단에서는 본연의 역활 요청 맵핑, 입력처리, 비즈니스 로직처리, 뷰 선택 등등의 역할만을 수행할 수 있습니다.
- username 과 password를 POST요청으로 보냄으로써 외부에 노출되지는 않지만 외부에 탈취할 수 있는 문제가 있다. 이를 해결하기 위해 HTTPS를 적용시켜서 보완해야합니다.
- JWT에서 로그아웃을 구현하는 것은 고려할 것이 많습니다.
  - 프론트단에서 로그아웃을 요청하면 프론트측에서 가지고 있는 JWT를 제거하면 됩니다.
  - 문제는 JWT를 제거하기전 탈취되어 버리면 제거했더라도 복사본을 들고 서버측으로 접근할 수 있습니다. 이에 따라 서버측에서 토큰을 기억(DB, Redis, 등등)하고 있다가 로그아웃 요청이 오면 해당 토큰을 블랙 리스트 처리합니다.
  - 위 구현을 위해서는 보통 Refresh 토큰, Access 토큰 2가지를 발급하고 Access  코튼에 대해서는 생명주기가 짧기 때문에 프론트단에서 JWT를 제거하는 방식으로 로그아웃을 진행하고, Refresh 토큰은 생명주기가 길기 때문에 서버측에서 미리 저장해둔 정보를 블랙리스트시켜 블랙리스트 처리된 토큰으로 요청이오면 거부하는 방식으로 작업을 진행합니다.
