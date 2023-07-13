package com.epam.esm.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * Parent of all entity.
 */
@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity{

    @Column
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
