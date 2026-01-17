package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "COMMENT", indexes = {
        @Index(name = "idx_comment__post_id", columnList = "POST_ID")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    @NotNull
    @Column(name = "NAME", length = 50)
    private String name;

    @NotNull
    @Column(name = "PW")
    private String pw;

    @NotNull
    @Column(name = "TEXT", columnDefinition = "TEXT")
    private String text;

    @NotNull
    @Column(name = "IS_SECRET", columnDefinition = "TINYINT(1)")
    private Boolean isSecret;

    @Builder
    public Comment(Post post, String name, String pw, String text, Boolean isSecret) {
        this.post = post;
        this.name = name;
        this.pw = pw;
        this.text = text;
        this.isSecret = (isSecret != null) ? isSecret : false;
    }
}
