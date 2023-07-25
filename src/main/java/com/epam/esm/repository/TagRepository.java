package com.epam.esm.repository;

import com.epam.esm.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends PagingAndSortingRepository<TagEntity, Long> {
    boolean existsByName(String name);
    Page<TagEntity> findAllByGiftCertificates(GiftCertificateRepository giftCertificateRepository, Pageable pageable);
}
