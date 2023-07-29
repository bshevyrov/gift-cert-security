package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity that represent tag table.
 */
@Entity
@Table(name = "tag")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TagEntity extends BaseEntity {
    @ManyToMany(mappedBy = "tagEntities",fetch = FetchType.LAZY)
    private List<GiftCertificateEntity> giftCertificateEntities;
}
