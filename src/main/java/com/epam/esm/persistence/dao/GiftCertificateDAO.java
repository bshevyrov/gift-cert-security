package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.GiftCertificateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GiftCertificateDAO extends CrudDAO<GiftCertificateEntity, Long> {
    Page<GiftCertificateEntity> findAllByTagsId(List<Long> tagsId,  Pageable pageable);
}
