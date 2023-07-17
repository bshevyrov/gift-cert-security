package com.epam.esm.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
public class Customer extends BaseEntity {
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() {
    }
}
