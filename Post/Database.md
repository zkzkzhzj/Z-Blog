## 데이터베이스 초기 생성
1. 데이터베이스를 생성한다.
2. 유저를 생성한다.
3. 생성한 유저에게 데이터베이스 권한을 준다.
4. 데이터베이스에 접근권한을 주는 계정을 관리하여 보안 및 파트너에게 접속 계정 정보를 알려준다.

데이터 베이스 연결하였고
현재 데이터 베이스 정보는 application-local 에 정의하였으며 계정 정보는 .env 로 관리하고있다.
접속정보를 local 로 빼놓는게 올바른 방법일까? 고민을 해봐야할 것 같다.

두번 감추는 것이 아니라 application-local dev prd 를 써써 구분 짓는 방법과
application 에 정의하지만 .env 로 불러오는 방법 혹은 더 다양한 방법을 생각해보자

## 데이터 베이스 모델링

### 1. MYSQL 데이터 타입
숫자형 데이터 타입
1. TINYINT(1byte) -> -128 ~ 127 또는 255 표현
2. SMALLINT(2byte) -> -32,768 ~ 32,767 또는 65536 표현
3. MEDIUMINT(3byte) -> -8,388,608 ~ 8,388,607 또는 16777215 표현
4. INT(4byte) -> 약 -21억 ~ 21 억 또는 약 42억 표현
5. BIGINT(8byte) -> 약 -900경 ~ 900경 또는 1800경 표현
6. FLOAT(4byte) -> 소수점 아래 7자리 표현
7. DOUBLE(8byte) -> 소수점 아래 15자리 표현
8. DECIMAL, NUMERIC(M, D) -> 전체 길이는 M 이며 그중 소수점은 D를 가지도록 표현

문자형 데이터 타입
1. CHAR(255byte) -> 문자형 고정 길이
2. VARCHAR(65535byte) -> 문자형 가변 길이
3. BINARY(255byte) -> 이진 데이터 고정 길이
4. VARBINARY(65535byte) -> 이진 데이터 가변 길이
5. TINYTEXT(255byte) -> TEXT 데이터 값
6. TEXT(65535byte) -> TEXT 데이터 값
7. MEDIUMTEXT(16777215byte) -> TEXT 데이터 값
8. LONGTEXT(약 42억byte) -> TEXT 데이터 값
9. TINYBLOB(255byte) -> BLOB 데이터 값
10. BLOB(65535byte) -> BLOB 데이터 값
11. MEDIUMBLOB(16777215byte) -> BLOB 데이터 값
12. LONGBLOB(약 42억byte) -> BLOB 데이터 값

시간 및 날짜형 데이터 타입
1. DATE(3byte) -> YYYY-MM-DD
2. TIME(3byte) -> HH:MM:SS
3. DATETIME(8byte) -> YYYY-MM-DD HH:MM:SS
4. TIMESTAMP(4byte) -> YYYY-MM-DD HH:MM:SS
5. YEAR(1byte) -> YYYY

* DATETIME 과 TIMESTAMP 의 차이
* TIMESTAMP 는 기본적으로 NOTNULL
* DATETIME 은 시스템의 TIME_ZONE 이 변경되어도 값이 변화하지 않지만 TIMESTAMP 는 영향을 받는다
* DATETIME 은 문자형으로 저장, TIMESTAMP 는 숫자형으로 저장
* DATETIME 범위('1000-01-01' ~ '9999-12-31'), TIMESTAMP 범위('1970-01-01 00:00:01' ~ '2038-01-19 03:14:07')
* TIMESTAMP 의 경우 1970-01-01 부터 흐른 시간을 저장하는데 이 범위가 4byte 이며 그 한계가 2038-01-19
* 왜 하필 1970-01-01 인가? UNIX 운영체제가 처음 개발되선 시기고 당시에 언제를 0초로 잡을까 고민하다가 나온 기준점
* TIMESTAMP 를 쓰게되면 필연적으로 맞닥뜨리는 문제인데 어떻게 해결해야할지 추후에 찾아보자

---

### 테이블 설계 초안(기본 기능 충실)

POST Table
1. ID(PK) -> BIGINT -> 게시글 ID
2. POST_KEY(NOTNULL) -> BINARY(16) -> 게시글 UUID
3. TITLE(NOTNULL) -> VARCHAR(255) -> 게시글 제목
4. FILE_NAME(NOTNULL) -> VARCHAR(255) -> 게시글 파일 이름
5. VIEWER_COUNT(NOTNULL) -> INT -> 조회수
6. SORT_KEY(NOTNULL) -> INT -> 게시글 순서 정렬 키 값
7. IS_HIDDEN -> TINYINT(1) -> 게시글 숨김 여부
8. CREATE_DATE -> TIMESTAMP -> 작성일자
9. UPDATE_DATE -> TIMESTAMP -> 수정일자
10. DELETE_DATE -> TIMESTAMP -> 삭제일자

COMMENT TABLE
1. ID(PK) -> BIGINT -> 댓글 ID
2. POST_ID(FK) -> BIGINT -> 게시글 ID
3. NAME(NOTNULL) -> VARCHAR(50) -> 댓글 작성자 이름
4. PW(NOTNULL) -> VARCHAR(255) -> 작성자 비밀번호(암호화)
5. TEXT(NOTNULL) -> TEXT -> 댓글 내용
6. IS_SECRET -> TINYINT(1) -> 비밀 댓글 여부
7. CREATE_DATE -> TIMESTAMP -> 작성일자
8. UPDATE_DATE -> TIMESTAMP -> 수정일자
9. DELETE_DATE -> TIMESTAMP -> 삭제일자

COMMENT_CLOSER TABLE
1. ID(PK) -> BIGINT -> PARENT_ID + CHILD_ID 복합 키
2. PARENT_ID(FK) -> BIGINT -> 부모 댓글 ID
3. CHILD_ID(FK) -> BIGINT -> 자식 댓글 ID
4. DEPTH -> INT -> 깊이

TAG TABLE
1. ID(PK) -> BIGINT -> 태그 ID
2. NAME(UNIQUE) -> VARCHAR(255) -> 태그 이름
3. CREATE_DATE -> TIMESTAMP -> 작성일자
4. UPDATE_DATE -> TIMESTAMP -> 수정일자
5. DELETE_DATE -> TIMESTAMP -> 삭제일자

POST_TAG TABLE
1. ID(PK) -> BIGINT -> POST_ID + TAG_ID 복합 키
2. POST_ID(FK) -> BIGINT -> 포스트 ID
3. TAG_IG(FK) -> BIGINT -> 태그 ID

CATEGORY TABLE
1. ID(PK) -> BIGINT -> 카테고리 ID
2. TITLE(NOTNULL) -> VARCHAR(255) -> 카테고리 제목
3. CREATE_DATE -> TIMESTAMP -> 작성일자
4. UPDATE_DATE -> TIMESTAMP -> 수정일자
5. DELETE_DATE -> TIMESTAMP -> 삭제일자

CATEGORY_CLOSER TABLE
1. ID(PK) -> BIGINT -> PARENT_ID + CHILD_ID 복합 키
2. PARENT_ID(FK) -> BIGINT -> 부모 카테고리 ID
3. CHILD_ID(FK) -> BIGINT -> 자식 카테고리 ID
4. DEPTH -> INT -> 깊이

## 데이터베이스 ERD
