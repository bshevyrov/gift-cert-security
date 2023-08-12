package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudDAO<E extends Entity, ID extends Number> {
    E create(E e);

    E update(E e);

    Optional<E> findById(Class<E> eClass,ID id);

    Optional<E> deleteById(Class<E> eClass,ID id);

    Page<E> findAll(Class<E> eClass,Pageable pageable);

}
