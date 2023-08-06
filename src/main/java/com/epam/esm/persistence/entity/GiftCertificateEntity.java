package com.epam.esm.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

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

@CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @CreationTimestamp

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
