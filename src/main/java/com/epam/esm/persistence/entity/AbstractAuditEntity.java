package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreRemove;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity{
    @CreatedDate
    @Nullable
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Nullable
    private LocalDateTime updatedDate;
        @Nullable
        @Column(updatable = false)
    private LocalDateTime deletedDate;

    @PreRemove
    public void onPreRemove() {
        audit();
    }

    private void audit() {
        deletedDate = LocalDateTime.now();
    }
}

