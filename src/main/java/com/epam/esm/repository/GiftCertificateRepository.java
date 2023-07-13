package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCertificateRepository extends PagingAndSortingRepository<GiftCertificate, Long> {
    Page<GiftCertificate> findAll(Pageable pageable);
}
