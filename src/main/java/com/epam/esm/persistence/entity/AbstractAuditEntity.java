package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreRemove;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity {
    @CreatedDate
    @Nullable
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Nullable
    private LocalDateTime updatedDate;
    @Nullable
    private LocalDateTime deletedDate;

    @PreRemove
    public void onPreRemove() {
        audit();
    }

    private void audit() {
        deletedDate = LocalDateTime.now();
    }
}

