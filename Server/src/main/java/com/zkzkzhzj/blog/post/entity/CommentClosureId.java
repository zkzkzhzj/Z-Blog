package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

// 재사용 가능한 컴포넌트 선언(복합키를 가지는 Entity 에서 사용)
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 복합키는 생성 시점에 모든 데이터가 정의되어야하며 불변이여야한다.
// 전체 데이터를 받아 사용하는 생성자 Annotation
@AllArgsConstructor
// TODO: JPA 영속석 컨텍스트 관리 방법 더 찾아보기(현재는 필수라는 정보 확인)
@EqualsAndHashCode
// TODO: Serializable 사용 찾아보기(현재는 필수라는 정보 확인)
public class CommentClosureId implements Serializable {
    private Long parentId;
    private Long childId;
}
