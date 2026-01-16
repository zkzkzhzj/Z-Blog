package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "CATEGORY", indexes = {
        @Index(name = "idx_category_parent_id", columnList = "PARENT_ID")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "SORT_KEY")
    private Integer sortKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @Builder
    public Category(String title, Category parent) {
        this.title = title;
        this.parent = parent;

        // 기본 값 할당
        this.sortKey = 999;
    }
}
