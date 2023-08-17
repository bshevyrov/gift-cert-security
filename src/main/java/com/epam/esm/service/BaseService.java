package com.epam.esm.service;

import com.epam.esm.persistence.entity.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<E extends Entity> {
    E create(E entity);

    E findById(long id);

    Page<E> findAll(Pageable pageable);

    void update(E entity);

    E delete(long id);
}
