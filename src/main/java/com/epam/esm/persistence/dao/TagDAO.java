package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.TagEntity;

import java.util.Optional;

public interface TagDAO extends CrudDAO<TagEntity,Long>{
    boolean existsByName(String tagEntityName);
    boolean existsById(Long tagEntityId);
    Optional<TagEntity> findByName(String tagEntityName);
}
