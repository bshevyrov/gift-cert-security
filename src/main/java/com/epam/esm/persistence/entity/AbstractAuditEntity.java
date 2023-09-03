package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Parent of all entity.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractAuditEntity implements Serializable {
    @Nullable
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Nullable
    private LocalDateTime updatedDate;
    @Nullable
    private LocalDateTime deletedDate;

    @PrePersist
    public void onPrePersist() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedDate = LocalDateTime.now();
    }

    @PreRemove
    public void onPreRemove() {
        deletedDate = LocalDateTime.now();
    }

}

