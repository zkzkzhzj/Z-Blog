package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "COMMENT_CLOSURE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentClosure {

    // 복합키 매핑
    // @Embeddable Annotation 설정이 되어있는 클래스를 가져올 수 있다
    @EmbeddedId
    private CommentClosureId id;

    // 복합키를 생성할 때 MapsId 값과 복합키 변수를 비교하여 자동으로 값을 넣어준다
    // TODO: 추가적인 장점이 있으므로 찾아보기!
    @MapsId("parentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @MapsId("childId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHILD_ID")
    private Comment child;

    @NotNull
    @Column(name = "DEPTH")
    private Integer depth;

    @Builder
    public CommentClosure(Comment parent, Comment child, Integer depth) {
        this.id = new CommentClosureId(parent.getId(), child.getId());
        this.parent = parent;
        this.child = child;
        this.depth = depth;
    }
}
