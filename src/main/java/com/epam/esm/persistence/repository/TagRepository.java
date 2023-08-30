package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.TagEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends PagingAndSortingRepository<TagEntity, Long> {
    boolean existsByName(String name);

    Optional<TagEntity> findByName(String name);
}
