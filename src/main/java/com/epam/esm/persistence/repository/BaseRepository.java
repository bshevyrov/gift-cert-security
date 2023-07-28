package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.entity.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BaseRepository<T extends BaseEntity, L extends Number> extends CrudRepository<BaseEntity, Long> {
}
