package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * com.epam.esm.persistence.entity.Entity that represent tag table.
 */
@Entity
@Table(name = "tag")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@ToString(exclude ="giftCertificateEntities" )
public class TagEntity extends BaseEntity implements com.epam.esm.persistence.entity.Entity {
    @Id
    @GeneratedValue(generator = "tag-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tag-generator", sequenceName = "tag_sequence", allocationSize = 10, initialValue = 50)

//    @Null
    private Long id;

    @ManyToMany(mappedBy = "tagEntities",cascade=CascadeType.ALL)
    private List<GiftCertificateEntity> giftCertificateEntities;

        }
