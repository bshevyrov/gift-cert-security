package com.epam.esm.persistence.entity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends BaseEntity {
    @OneToMany(mappedBy = "customerEntity")
    private List<OrderEntity> orderEntities;
}
