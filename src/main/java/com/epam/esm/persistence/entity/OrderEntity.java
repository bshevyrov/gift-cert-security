package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "customerEntity")
public class OrderEntity extends AbstractAuditEntity implements com.epam.esm.persistence.entity.Entity {
    @Id
    @GeneratedValue(generator = "order-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order-generator", sequenceName = "order_sequence", allocationSize = 10, initialValue = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

//    @OneToMany(mappedBy = "orderEntity",cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH},orphanRemoval = true)
//    private List<OrderItemEntity> orderItemEntities;

    @Positive(message = "Internal error with field cost.")
    private double cost;
}
