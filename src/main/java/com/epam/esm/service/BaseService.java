package com.epam.esm.service;

import com.epam.esm.persistence.entity.AbstractEntity;
import com.epam.esm.persistence.entity.Entity;

import java.util.List;

public interface BaseService<E extends Entity> {
    E create(E entity);

    E findById(long id);

    List<E> findAll();

    void update(E entity);

    void delete(long id);

}
