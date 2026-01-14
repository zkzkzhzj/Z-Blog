package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * 참고
 * JPA -> https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html
 * Hibernate ORM -> https://docs.hibernate.org/orm/current/userguide/html_single/#mapping-types
 */

@Entity
// 인덱스 추가
@Table(name = "POST", indexes = {
        @Index(name = "idx_post_key", columnList = "POST_KEY", unique = true),
        @Index(name = "idx_post_sort", columnList = "SORT_KEY")
})
public class Post {

    @Id
    // Oracle, PostGreSQL 의 경우 시퀀스 오브젝트로 PK 를 증가시킨다. GenerationType.Sequence -> 시퀀스명 연결
    // MySQL 의 경우 테이블 자체에서 PK 를 관리하기 때문에 데이터가 들어가는 순간 PK 번호 부여(IDENTIFY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글 UUID(값은 중복되지 않고 NULL 을 허용하지 않음)
    // application 검증 -> NotNull Annotation / Table 생성 제약 조건 -> nullable
    // columnDefinition -> 컬럼 데이터 타입 지정을 위해 사용
    @NotNull
    @Column(name = "POST_KEY", columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID postKey;

    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotNull
    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @NotNull
    @Column(name = "VIEW_COUNT", nullable = false)
    private Integer viewCount = 0;

    @NotNull
    @Column(name = "SORT_KEY", nullable = false)
    private Integer sortKey = 999;

    @NotNull
    @Column(name = "IS_HIDDEN", nullable = false)
    private Boolean isHidden = false;

    // Category ID 의 경우 외래키 속성은 안넣더라도 OneToMany 라는 속성을 줘야한다고 알고 있어 학습 후 추가하자
    // 공통된 생성일, 수정일, 삭제일 컬럼의 경우 상속으로 관리할 수 있을 것 같아 우선적으로 보류
}
