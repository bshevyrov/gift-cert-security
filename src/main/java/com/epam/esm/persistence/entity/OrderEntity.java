package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"order\"")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends AbstractEntity {
    @ManyToOne(
//            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;
    //    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.LAZY)
//    private List<OrderItemEntity> orderItemEntities;
    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime createTime;
    private double cost;
}
