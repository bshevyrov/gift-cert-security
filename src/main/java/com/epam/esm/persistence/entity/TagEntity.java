package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
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
@SuperBuilder
@ToString(exclude = "giftCertificateEntities")
public class TagEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "tag-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tag-generator", sequenceName = "tag_sequence", allocationSize = 10, initialValue = 50)
    private Long id;

    @ManyToMany(mappedBy = "tagEntities", cascade = CascadeType.ALL)
    private List<GiftCertificateEntity> giftCertificateEntities;
}
