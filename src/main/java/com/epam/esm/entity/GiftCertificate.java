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

    @OneToMany(mappedBy = "order_item")
    private List<Order> orders;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GiftCertificate that = (GiftCertificate) o;
        return Double.compare(that.price, price) == 0 && duration == that.duration && Objects.equals(description, that.description) && Objects.equals(createDate, that.createDate) && Objects.equals(lastUpdateDate, that.lastUpdateDate) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, price, duration, createDate, lastUpdateDate, tags);
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", tags=" + tags +
                "} " + super.toString();
    }

    public GiftCertificate() {
    }
}
