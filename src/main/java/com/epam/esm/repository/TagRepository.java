package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
    boolean existsByName(String name);
    Page<Tag> findAllByGiftCertificates(GiftCertificateRepository giftCertificateRepository, Pageable pageable);
}
