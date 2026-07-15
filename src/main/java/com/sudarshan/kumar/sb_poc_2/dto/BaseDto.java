package com.sudarshan.kumar.sb_poc_2.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@SoftDelete(strategy=SoftDeleteType.DELETED)
public class BaseDto {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false, updatable = false)
    private LocalDateTime modifiedAt;
}
