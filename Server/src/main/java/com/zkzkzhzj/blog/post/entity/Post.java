package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 참고
 * JPA -> https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html
 * Hibernate ORM -> https://docs.hibernate.org/orm/current/userguide/html_single/#mapping-types
 * Lombok -> https://projectlombok.org/features/
 */

/**
 * 테이블 작성 초안 완료
 *
 * 진행 해야할 작업 LIST
 * 1. 공식문서를 살펴보며 잘못 사용한 Annotation 또는 적용하면 좋을 Annotation 확인
 * 2. 공통된 컬럼은 공통 Entity 클래스로 뺴서 적용하기
 * 3. 생성자 추가해주기
 */

@Entity
// 인덱스 추가
@Table(name = "POST", indexes = {
        @Index(name = "idx_post_key", columnList = "POST_KEY", unique = true),
        @Index(name = "idx_post_sort", columnList = "SORT_KEY")
})
// JPA 는 프록시를 사용한다 -> 가짜 객체를 만들어서 사용
// 생성자를 관리하기 위해 AccessLevel 을 PUBLIC 으로 열어버리면 연관되지 않은 클래스에서도 객체 생성이 가능해서 불완전한 객체가 만들어질 가능성이 커진다.
// PRIVATE 으로 접근을 막게되면 JPA 에서 접근이 불가능해져서 오류가 발생한다.
// 그렇기 때문에 상속관계에서 접근가능한 PROTECTED 설정을 두어 관리한다
// 추가적으로 하단에 생성자를 만들어서 @Builder 까지 적용하면 기본 값도 할당할 수 있고 추가적으로 컬럼 값도 편리하게 받을 수 있는 생성자가 완성된다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 생성, 수정, 삭제 등 감사 정보를 기록하기 위해 리스너 등록
@EntityListeners(AuditingEntityListener.class)
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

    // 테이블 연관 관계 정의
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    // 생성일자 자동으로 기록
    @CreatedDate
    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDateTime createDate;

    // 수정일자 자동으로 기록
    @LastModifiedDate
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "DELETE_DATE")
    private LocalDateTime deleteDate;
}
