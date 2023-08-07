package com.epam.esm.persistence.entity;


import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.epam.esm.util.validation.group.TagCreateValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Parent of all entity.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends AbstractEntity {
    @NotBlank(groups = {GiftCertificateCreateValidationGroup.class,
            TagCreateValidationGroup.class},
            message = "Name is mandatory")
    @Pattern(regexp = "[a-zA-Z]+", message = "Name contains illegal chars")
    private String name;
}
