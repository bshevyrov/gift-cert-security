package com.epam.esm.service.impl;

import com.epam.esm.entity.OrderEntity;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity create(OrderEntity entity) {
        return null;
    }

    @Override
    public OrderEntity findById(long id) {
        return null;
    }

    @Override
    public List<OrderEntity> findAll() {
        return null;
    }

    @Override
    public void update(OrderEntity entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Page<OrderEntity> findAllByCustomerId(Long id, Pageable pageable) {
        return orderRepository.findAllByCustomer_Id(id,pageable);
    }
}
