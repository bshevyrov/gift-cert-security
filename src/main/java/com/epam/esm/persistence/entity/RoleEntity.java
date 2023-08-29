package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@ToString(exclude = "customerEntities")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roleEntities", fetch = FetchType.LAZY)
    private List<CustomerEntity> customerEntities;
}
