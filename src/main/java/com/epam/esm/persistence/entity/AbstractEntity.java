package com.epam.esm.persistence.entity;

import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.epam.esm.util.validation.group.GiftCertificateUpdateValidationGroup;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@MappedSuperclass
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractEntity extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = GiftCertificateUpdateValidationGroup.class)
    @Null(groups = GiftCertificateCreateValidationGroup.class)
    private Long id;
}
