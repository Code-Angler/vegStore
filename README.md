# 청과물 가게

### 개발방향
- RestAPI 형태로 client와 통신
- 오픈 API를 server 내부에서 호출하여, 해당 값을 parsing하여 response값으로 사용
- client에서 API통신으로 얻은 accessToken값을 sessionStorge에 저장
- 이후 관련 요청시 header에 해당 accessToken 값을 보내는 것으로 비지니스형태를 채택
   그 이유는 일회성의 데이터를 데이터화 시켜 DB에 보관하는것이 무의미 했으므로, 필요한 값을 client 에서 redirect 시키는 형태로 선택
- 반복되는 형태의 http관련 code는 Utils에서 method화 하는 작업
 
