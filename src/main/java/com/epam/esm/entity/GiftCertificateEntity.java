package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity that represent gift_certificate table.
 */
@Entity
@Table(name = "gift_certificate")
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
public class GiftCertificateEntity extends BaseEntity {
    private String description;
    private double price;
    private int duration;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "gift_certificate_has_tag",
            joinColumns = {@JoinColumn(name = "gift_certificate_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<TagEntity> tagEntities;
    @OneToMany(mappedBy = "giftCertificate")
    private List<OrderItemEntity> orderItemEntities;
}
