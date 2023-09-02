package com.epam.esm.persistence.entity;

import com.epam.esm.persistence.entity.type.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
public class CustomerEntity extends AbstractAuditEntity {
    @Id
    @GeneratedValue(generator = "customer-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer-generator", sequenceName = "customer_sequence", allocationSize = 10, initialValue = 50)
    private Long id;

    @Nullable
    @OneToMany(mappedBy = "customerEntity")
    private List<OrderEntity> orderEntities;

    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[\\w]+$", message = "Username contains illegal chars.")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @NotEmpty
    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "customer_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleEntity> roleEntities;
}




