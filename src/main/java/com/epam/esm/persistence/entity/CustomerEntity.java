package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends BaseEntity {
    @OneToMany(mappedBy = "customerEntity")
    private List<OrderEntity> orderEntities;
}
