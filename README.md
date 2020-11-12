# MSA Study Project

### 프로젝트 목적
간단한 쇼핑몰을 주제로 MSA 구성 이해  
<hr>

### 기술 습득 목표  
SpringBoot  
SpringCloud  
SpringDataJPA  
MessageQueue  
SpringMustache  
MySQL  
~~SpringSecurity~~

## 모듈 구성
msa-eureka-server  
- API-Gateway 및 각 API, Web들을 하나로 묶는 서버
- port : 8761

msa-apigateway
- API Gateway 서버로써 클라이언트의 모든 요청을 받아 URL에 해당하는 API서버로 전달
- 추후 인증기능을 별도로 구현할수도있지만 gateway에서 구현할 예정
- port : 8090

msa-order
- 주문 서비스
- port: 8005

msa-product
- 상품 서비스
- port : 8007

msa-member
- 회원 서비스
- port : 8006

msa-web
- 클라이언트에게 폼 제공 서비스
- port : 8080
