package com.epam.esm.service.impl;

import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public OrderEntity create(OrderEntity entity) {
        return orderDAO.create(entity);
    }

    @Override
    public OrderEntity findById(long id) {
        return null;
    }

    @Override
    public Page<OrderEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(OrderEntity entity) {

    }

    @Override
    public OrderEntity delete(long id) {
        return null;
    }

    @Override
    public Page<OrderEntity> findAllByCustomerId(Long id, Pageable pageable) {
        return orderDAO.findAllByCustomerEntityId(id, pageable);
    }

    @Override
    public OrderEntity getPopularTagInOrderByCustomerId(Long id) {
        Optional<OrderEntity> popularTagInOrderByCustomerId = orderDAO.getPopularTagInOrderByCustomerId(id);
        return popularTagInOrderByCustomerId.get();
    }


}
