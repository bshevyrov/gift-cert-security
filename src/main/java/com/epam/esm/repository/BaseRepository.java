package com.epam.esm.repository;

import com.epam.esm.entity.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BaseRepository<T extends BaseEntity, L extends Number> extends CrudRepository<BaseEntity, Long> {
}
