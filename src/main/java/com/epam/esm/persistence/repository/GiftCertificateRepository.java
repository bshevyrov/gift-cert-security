package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.entity.GiftCertificateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCertificateRepository extends PagingAndSortingRepository<GiftCertificateEntity, Long> {
    Page<GiftCertificateEntity> findAll(Pageable pageable);

}
