package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class CustomerEntity extends BaseEntity implements com.epam.esm.persistence.entity.Entity {
    @Id
    @GeneratedValue(generator = "customer-generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customer-generator",sequenceName= "customer_sequence",allocationSize = 10,initialValue = 50)


    private Long id;

    @OneToMany(mappedBy = "customerEntity"
//            ,cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH}
//            , orphanRemoval = true
    )
    private List<OrderEntity> orderEntities;
}




