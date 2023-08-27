package com.epam.esm.persistence.entity;

import com.epam.esm.persistence.entity.type.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * Entity that represent customer table.
 */
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class CustomerEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "customer-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer-generator", sequenceName = "customer_sequence", allocationSize = 10, initialValue = 50)
    private Long id;

    @OneToMany(mappedBy = "customerEntity")
    private List<OrderEntity> orderEntities;
    private String username;
    private String password;
    private Status status;
//    @Column(name = "role")
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn (name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<RoleEntity> roleEntities;
}




