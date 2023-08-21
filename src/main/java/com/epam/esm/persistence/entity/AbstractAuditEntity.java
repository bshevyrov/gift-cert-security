package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Parent of all entity.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractAuditEntity {
    @Nullable
    @Column(updatable = false)
    private LocalDateTime createdDate;

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

