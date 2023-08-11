package com.epam.esm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GiftCertificateTagEntity extends AbstractAuditEntity implements Entity{
    private long giftCertificateId;
    private long TagId;
}
