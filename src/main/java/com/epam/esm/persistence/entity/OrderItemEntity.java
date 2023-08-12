package com.epam.esm.persistence.entity;

import com.epam.esm.util.validation.group.GiftCertificateCreateValidationGroup;
import com.epam.esm.util.validation.group.GiftCertificateUpdateValidationGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "order_item")
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderItemEntity extends AbstractEntity implements com.epam.esm.persistence.entity.Entity {
    @Id
    @GeneratedValue(generator = "orderitem-generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orderitem-generator",sequenceName= "order_item_sequence",allocationSize = 10,initialValue = 50)
    @Null
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificateEntity giftCertificateEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
    @Positive(message = "Internal error with field cost.")
    private int quantity;
}
