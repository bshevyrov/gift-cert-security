package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * Entity that represent tag table.
 */
@Entity
@Table(name = "tag")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TagEntity extends BaseEntity {
    @ManyToMany(mappedBy = "tagEntities")
    private List<GiftCertificateEntity> giftCertificateEntities;
}
