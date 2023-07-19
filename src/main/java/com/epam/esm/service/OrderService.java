package com.epam.esm.service;

import com.epam.esm.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService extends BaseService<Order>{
  Page<Order> findAllByCustomerId(Long id, Pageable pageable);
}
