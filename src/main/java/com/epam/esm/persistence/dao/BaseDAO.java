package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.AbstractAuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseDAO<E extends AbstractAuditEntity, ID extends Number> {
    E create(E e);

    E update(E e);

    Optional<E> findById(Class<E> eClass, ID id);

    Optional<E> deleteById(Class<E> eClass, ID id);

    Page<E> findAll(Class<E> eClass, Pageable pageable);

    Boolean existsById(Class<E> e, ID id);

    ID count(Class<E> e);


}
