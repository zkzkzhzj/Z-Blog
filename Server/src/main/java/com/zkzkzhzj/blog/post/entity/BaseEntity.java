package com.zkzkzhzj.blog.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 데이터 조회를 위한 Getter 사용
@Getter
// 테이블은 생성하지 않으며 컬럼 정보만 공유
@MappedSuperclass
// 생성, 수정, 삭제 등 감사 정보를 기록하기 위해 리스너 등록
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

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

    // 삭제일자 기록
    public void delete() {
        this.deleteDate = LocalDateTime.now();
    }
}
