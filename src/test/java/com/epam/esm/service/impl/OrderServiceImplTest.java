package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderServiceImpl orderService;

    @Test
    void create() {
        assertDoesNotThrow(() -> orderService.create(new OrderEntity()));
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> orderService.findById(0L));
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> orderService.findAll(Pageable.unpaged()));
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> orderService.update(new OrderEntity()));
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> orderService.delete(0L));
    }

    @Test
    void findAllByCustomerId() {
        assertDoesNotThrow(() -> orderService.findAllByCustomerId(0L, Pageable.unpaged()));
    }

    @Test
    void getPopularTagInOrderByCustomerId() {
        assertDoesNotThrow(() -> orderService.getPopularTagInOrderByCustomerId(any()));
    }
}