package com.epam.esm.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Entity that represent tag table.
 */
@Entity
@Table

public class Tag extends BaseEntity {

    @ManyToMany(mappedBy = "tags")
    private List<GiftCertificate> giftCertificates ;

    public Tag() {
    }

    public List<GiftCertificate> getGiftCertificates() {
        return giftCertificates;
    }

    public void setGiftCertificates(List<GiftCertificate> giftCertificates) {
        this.giftCertificates = giftCertificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(giftCertificates, tag.giftCertificates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giftCertificates);
    }
}
