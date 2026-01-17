package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
// 게시글 내부 태그를 가져오기 위한 복합 인덱스 + 태그 별 포스트 모음을 보기 위한 단일 인덱스
@Table(name = "TAG", indexes = {
        @Index(name = "idx_tag__post_id__name", columnList = "POST_ID, NAME"),
        @Index(name = "idx_tag_name", columnList = "NAME")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public void Tag(Post post, String name) {
        this.post = post;
        this.name = name;
    }
}
