package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.persistence.repository.OrderRepository;
import com.epam.esm.persistence.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService ;
    @Test
    void create() {
    }

    @Test
    void findById() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                ()->orderService.findById(0L));
    }

    @Test
    void findAll() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                ()->orderService.findAll(Pageable.unpaged()));
    }

    @Test
    void update() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                ()->orderService.update(new OrderEntity()));
    }

    @Test
    void delete() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                ()->orderService.delete(0L));
    }

    @Test
    void findAllByCustomerId() {
    }

    @Test
    void getPopularTagInOrderByCustomerId() {
    }
}