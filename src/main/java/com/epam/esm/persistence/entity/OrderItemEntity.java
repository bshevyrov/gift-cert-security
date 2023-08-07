package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "order_item")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderItemEntity extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificateEntity giftCertificateEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
    @Positive(message = "Internal error with field cost.")
    private int quantity;
}
