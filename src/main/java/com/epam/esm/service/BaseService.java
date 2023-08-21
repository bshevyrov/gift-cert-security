package com.epam.esm.service;

import com.epam.esm.persistence.entity.AbstractAuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<E extends AbstractAuditEntity> {
    E create(E entity);

    E findById(long id);

    Page<E> findAll(Pageable pageable);

    E update(E entity);

    E delete(long id);
}
