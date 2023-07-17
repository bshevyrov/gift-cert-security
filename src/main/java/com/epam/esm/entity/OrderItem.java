package com.epam.esm.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "gift_certificate_id")
    private GiftCertificate giftCertificate;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private int quantity;

    public OrderItem() {
    }

    public GiftCertificate getGiftCertificates() {
        return giftCertificate;
    }

    public void setGiftCertificates(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
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
