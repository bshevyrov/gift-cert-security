package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GiftCertificateService extends BaseService<GiftCertificateEntity> {

    Page<GiftCertificateEntity> findAll(Pageable pageable);
}
