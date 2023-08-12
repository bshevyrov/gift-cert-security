package com.epam.esm.persistence.entity;

import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.epam.esm.util.validation.group.GiftCertificateUpdateValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
public class TagEntity extends BaseEntity implements com.epam.esm.persistence.entity.Entity {
    @Id
    @GeneratedValue(generator = "tag-generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tag-generator",sequenceName= "tag_sequence",allocationSize = 10,initialValue = 50)

    @Null
    private Long id;
//    @ManyToMany(mappedBy = "tagEntities")
//    private List<GiftCertificateEntity> giftCertificateEntities;

}
