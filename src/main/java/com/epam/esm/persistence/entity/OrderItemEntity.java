package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Positive;
/**
 * Entity that represent order_item table.
 */
@Entity
@Table(name = "order_item")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString(exclude = "orderEntity")
public class OrderItemEntity extends AbstractAuditEntity {
    @Id
    @GeneratedValue(generator = "orderitem-generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orderitem-generator", sequenceName = "order_item_sequence", allocationSize = 10, initialValue = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificateEntity giftCertificateEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @Positive(message = "Internal error with field cost.")
    private int quantity;
}
