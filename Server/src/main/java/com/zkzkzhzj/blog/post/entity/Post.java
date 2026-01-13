package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 참고
 * JPA -> https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html
 * Hibernate ORM -> https://docs.hibernate.org/orm/current/userguide/html_single/#mapping-types
 */

@Entity
class Post {

    @Id
    // Oracle, PostGreSQL 의 경우 시퀀스 오브젝트로 PK 를 증가시킨다. GenerationType.Sequence -> 시퀀스명 연결
    // MySQL 의 경우 테이블 자체에서 PK 를 관리하기 때문에 데이터가 들어가는 순간 PK 번호 부여(IDENTIFY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
