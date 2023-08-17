package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractAuditEntity {
    //    @CreatedDate
    @Nullable
    @Column(updatable = false)
    private LocalDateTime createdDate;

    //    @LastModifiedDate
    @Nullable
    private LocalDateTime updatedDate;

    @PrePersist
    public void onPrePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedDate = LocalDateTime.now();
    }
}

