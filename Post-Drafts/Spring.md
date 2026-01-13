## 블로그 개발하며 배우는 스프링

### 공식문서
https://spring.io/

---

강의 요약
 - 무엇인가 반복되면 줄일 수 있는 방법이 있다
 - 만약 줄이지 못한다면 설계를 다시 한번 생각해보자

1. Entity, DTO 에 Builder 패턴 사용 및 final 불변 선언 추천
2. Response 를 builder 하는 부분은 Service 추천(응답 전용 interface 사용)
3. 페이징 처리 -> Pageable
4. 커스텀 에러 사용하자 -> *Exception 클래스 상속받아서 커스텀
5. Spring Rest Docs 이란것도 있다
6. 인증 처리
   1. @RequestParam 또는 @RequestHeader 사용
   2. 
7. 