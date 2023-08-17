package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderDAO extends BaseDAO<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerEntityId(Long id, Pageable pageable);

    Optional<OrderEntity> getPopularTagInOrderByCustomerId(Long id);
}
