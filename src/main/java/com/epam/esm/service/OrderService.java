package com.epam.esm.service;

import com.epam.esm.persistence.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService extends BaseService<OrderEntity> {
    Page<OrderEntity> findAllByCustomerId(Long id, Pageable pageable);

    OrderEntity getPopularTagInOrderByCustomerId(Long id);

}
