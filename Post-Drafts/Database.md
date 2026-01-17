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

- DATETIME 과 TIMESTAMP 의 차이
- TIMESTAMP 는 기본적으로 NOTNULL
- DATETIME 은 시스템의 TIME_ZONE 이 변경되어도 값이 변화하지 않지만 TIMESTAMP 는 영향을 받는다
- DATETIME 은 문자형으로 저장, TIMESTAMP 는 숫자형으로 저장
- DATETIME 범위('1000-01-01' ~ '9999-12-31'), TIMESTAMP 범위('1970-01-01 00:00:01' ~ '2038-01-19 03:14:07')
- TIMESTAMP 의 경우 1970-01-01 부터 흐른 시간을 저장하는데 이 범위가 4byte 이며 그 한계가 2038-01-19
- 왜 하필 1970-01-01 인가? UNIX 운영체제가 처음 개발되선 시기고 당시에 언제를 0초로 잡을까 고민하다가 나온 기준점
- TIMESTAMP 를 쓰게되면 필연적으로 맞닥뜨리는 문제인데 어떻게 해결해야할지 추후에 찾아보자

---

### 테이블 설계 초안(기본 기능 충실)

POST Table

1. ID(PK) -> BIGINT -> 게시글 ID
2. POST_KEY(NOTNULL) -> BINARY(16) -> 게시글 UUID
3. TITLE(NOTNULL) -> VARCHAR(255) -> 게시글 제목
4. FILE_NAME(NOTNULL) -> VARCHAR(255) -> 게시글 파일 이름
5. VIEW_COUNT(NOTNULL) -> INT -> 조회수
6. SORT_KEY(NOTNULL) -> INT -> 게시글 순서 정렬 키 값
7. IS_HIDDEN -> TINYINT(1) -> 게시글 숨김 여부
8. CATEGORY_ID(FK) -> BIGINT -> 포스트가 위치 할 카테고리 ID
9. CREATE_DATE -> TIMESTAMP -> 작성일자
10. UPDATE_DATE -> TIMESTAMP -> 수정일자
11. DELETE_DATE -> TIMESTAMP -> 삭제일자

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

1. ID(PK) -> BIGINT -> 태그 PK
2. POST_ID(FK) -> BIGINT -> 포스트 ID
3. NAME -> VARCHAR(255) -> 태그 이름
4. CREATE_DATE -> TIMESTAMP -> 작성일자
5. UPDATE_DATE -> TIMESTAMP -> 수정일자
6. DELETE_DATE -> TIMESTAMP -> 삭제일자

CATEGORY TABLE -> Closer Table 삭제(너무 과하다고 판단)

1. ID(PK) -> BIGINT -> 카테고리 ID
2. TITLE(NOTNULL) -> VARCHAR(255) -> 카테고리 제목
3. PARENT_ID -> BIGINT -> 카테고리 부모 ID(고민중...)
4. SORT_KEY(NOTNULL) -> INT -> 카테고리 순서 정렬 키 값
5. CREATE_DATE -> TIMESTAMP -> 작성일자
6. UPDATE_DATE -> TIMESTAMP -> 수정일자
7. DELETE_DATE -> TIMESTAMP -> 삭제일자

### 2026-01-13

FK(외래키)는 쓰는게 좋을까?

당연하게 외래키를 잡는 것이 데이터 무결성에 좋다고 생각하고 잡아야한다고 생각했다.

다만 내가 일했던 곳들을 되돌아보면 실제로 외래키를 DB에서 잡았던 곳은 없었다.

왜 그럴까 한번 찾아보고 생각해봤다.

1. 성능 저하(락)
    1. DB 가 데이터를 넣거나 수정할 때 외래키가 유효한지 연결된 테이블을 조회하는데 이 떄 무결성을 위해 락을 걸어버린다.
    2. 외래키에 인덱스가 잡혀있지 않으면 풀 스캔을 때려버린다.
2. 데이터 관리가 너무 어렵다(테스트 데이터 생성, 급한 수정, 마이그레이션 등)
3. 최상위 데이터를 삭제하면 연결된 데이터들도 삭제되는데 이는 데이터 유실로도 이어질 수 있다.

그래서 논리적으로 FK 관리를 하는 곳이 많은 것 이다.

물론 논리적으로 관리하기 때문에 정말 신경을 잘써야한다.

### 2026-01-14

테이블 생성할 때 Index 는 어떤 컬럼을 기준으로 잡아야 효율적일까?

1. 조회할 때 조건으로 잡기 좋은 컬럼
2. 인덱스를 잡을 때 고려해야할 조건 4가지
    1. 카디널리티(Cardinality)가 높은 컬럼 -> 컬럼이 가지고 있는 값의 중복도가 낮다
    2. 선택도(Selectivity)가 낮은 컬럼 -> 컬럼이 가지고 있는 값으로 적은 row 가 조회된다
    3. 조회 활용도가 높은 컬럼 -> 조회할 때 조건으로 잡기 좋은 컬럼
    4. 수정 빈도가 낮은 컬럼 -> 인덱스를 잡게되면 별도의 데이터 저장공간으로 관리하게 되는데 원본이 수정되면 인덱스 테이블도 수정
3. 인덱스를 잡게 되면 값을 조회할 때 좋지만 INSERT 에는 좋지 않고 UPDATE/DELETE 에서는 크게 유리하지는 않다
    1. INSERT 의 경우 데이터 기입이기 때문에 인덱스 테이블에 값을 추가하는 추가 작업만 수행
    2. UPDATE/DELETE 의 경우 수정하거나 삭제할 컬럼을 찾는데는 도움이되나 수정/삭제 자체에서는 유리하지 않음

## 데이터베이스 ERD
