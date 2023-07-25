package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity that represent tag table.
 */
@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class TagEntity extends BaseEntity {
    @ManyToMany(mappedBy = "tags")
    private List<GiftCertificateEntity> giftCertificateEntities;
}
