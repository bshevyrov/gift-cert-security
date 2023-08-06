package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity that represent gift_certificate table.
 */
@Entity
@Table(name = "gift_certificate")
@EntityListeners(AuditingEntityListener.class)
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DynamicUpdate
public class GiftCertificateEntity extends BaseEntity {

    @NotEmpty(message = "Internal error with field description.")
    @Pattern(regexp = "^(?! )[A-Za-z\\s]*$", message = "Internal error with field description.")
    private String description;

    @Min(value = 0, message = "Internal error with field price.")
    private double price;

    @Min(value = 1, message = "Internal error with field duration.")
    private int duration;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "gift_certificate_has_tag",
            joinColumns = {@JoinColumn(name = "gift_certificate_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<TagEntity> tagEntities;
}
