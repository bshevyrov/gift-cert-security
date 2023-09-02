package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@ToString(exclude = "customerEntities")
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roleEntities", fetch = FetchType.LAZY)
    private List<CustomerEntity> customerEntities;

    @Override
    public String getAuthority() {
        return name;
    }
}
