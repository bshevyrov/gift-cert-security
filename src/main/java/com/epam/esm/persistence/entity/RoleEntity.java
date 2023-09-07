package com.epam.esm.persistence.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@ToString(exclude = "customerEntities")
public class RoleEntity implements GrantedAuthority {
    public static final RoleEntity ROLE_USER = getRoleUser();
    public static final RoleEntity ROLE_ADMIN = getRoleAdmin();
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

    private static RoleEntity getRoleUser(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("ROLE_USER");
        return roleEntity;
    }
    private static RoleEntity getRoleAdmin(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("ROLE_ADMIN");
        return roleEntity;
    }
}
