package com.epam.esm.persistence.entity;

import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.epam.esm.util.validation.group.GiftCertificateUpdateValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.List;

/**
 * com.epam.esm.persistence.entity.Entity that represent gift_certificate table.
 */
@Entity
@Table(name = "gift_certificate")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class GiftCertificateEntity extends BaseEntity implements com.epam.esm.persistence.entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(groups = GiftCertificateUpdateValidationGroup.class)
    @Null(groups = GiftCertificateCreateValidationGroup.class)
    private Long id;
    @NotEmpty(groups = {GiftCertificateCreateValidationGroup.class,
            GiftCertificateUpdateValidationGroup.class},
            message = "Internal error with field description.")
    @Pattern(regexp = "^(?! )[A-Za-z\\s]*$", message = "Internal error with field description.")
    private String description;

    @Positive(groups = {GiftCertificateCreateValidationGroup.class,
            GiftCertificateUpdateValidationGroup.class},
            message = "Internal error with field price.")
    private double price;

    @Positive(groups = {GiftCertificateCreateValidationGroup.class,
            GiftCertificateUpdateValidationGroup.class},
            message = "Internal error with field duration.")
    private int duration;

    @ManyToMany
//            (cascade = CascadeType.PERSIST)
            (cascade = CascadeType.ALL)
    @JoinTable(
            name = "gift_certificate_has_tag",
            joinColumns = {@JoinColumn(name = "gift_certificate_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<TagEntity> tagEntities;
}
