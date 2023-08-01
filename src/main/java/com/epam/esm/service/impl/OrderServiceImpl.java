package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.persistence.repository.OrderRepository;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

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
        return orderRepository.findAllByCustomerEntity_Id(id,pageable);
    }

    @Override
    public OrderEntity getPopularTagInOrderByCustomerId(Long id) {
        Optional<OrderEntity> popularTagInOrderByCustomerId = orderRepository.getPopularTagInOrderByCustomerId(id);
        return popularTagInOrderByCustomerId.get();
    }


}
