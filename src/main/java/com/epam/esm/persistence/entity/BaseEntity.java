package com.epam.esm.persistence.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Parent of all entity.
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends AbstractEntity {
    @NotBlank(message="Name is mandatory")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name contains illegal chars")
    private String name;
}
