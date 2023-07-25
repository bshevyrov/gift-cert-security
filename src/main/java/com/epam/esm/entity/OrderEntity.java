package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;
    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> orderItemEntityList;
    @Column(name = "creation_time")
    private LocalDateTime createTime;
}
