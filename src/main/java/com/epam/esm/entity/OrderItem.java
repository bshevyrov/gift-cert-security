package com.epam.esm.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificate giftCertificates;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "quantity")
    private int quantity;

    public GiftCertificate getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(GiftCertificate giftCertificates) {
        this.giftCertificates = giftCertificates;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
