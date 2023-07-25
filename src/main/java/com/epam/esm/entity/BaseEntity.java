package com.epam.esm.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

/**
 * Parent of all entity.
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEntity extends AbstractEntity {

    private String name;

}
