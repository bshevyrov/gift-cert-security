package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends PagingAndSortingRepository<TagEntity, Long> {
    boolean existsByName(String name);
    @Query(value = "SELECT gc.tagEntities FROM  GiftCertificateEntity gc WHERE gc.id=?1")
    Page<TagEntity> findAllByGiftCertificateEntityId(Long id, Pageable pageable);
}
