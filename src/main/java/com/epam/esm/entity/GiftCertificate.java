package com.epam.esm.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Entity that represent gift_certificate table.
 */
@Entity
@Table(name = "gift_certificate")
public class GiftCertificate extends BaseEntity {
    @Column
    private String description;
    @Column

    private double price;
    @Column

    private int duration;
    @Column(name = "create_date")

    private LocalDateTime createDate;
    @Column(name = "last_update_date")

    private LocalDateTime lastUpdateDate;
    @ManyToMany
    @JoinTable(
            name = "gift_certificate_has_tag",
            joinColumns = { @JoinColumn(name = "gift_certificate_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )

    private List<Tag> tags;

    @OneToMany(mappedBy = "giftCertificate")
    private List<OrderItem> orderItems;
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<OrderItem> getOrders() {
        return orderItems;
    }

    public void setOrders(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public GiftCertificate() {
    }
}
