package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.AbstractAuditEntity;
import com.epam.esm.persistence.entity.Entity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<E extends Entity, ID extends Number> {
    E create(E e);

    E update(E e);

    Optional<E> findById(ID id);

    Optional<E> deleteById(ID id);

    List<E> findAll(Pageable pageable);
}
