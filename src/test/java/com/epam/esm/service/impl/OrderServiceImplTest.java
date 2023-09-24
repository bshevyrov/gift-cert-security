package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
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
        assertThrowsExactly(UnsupportedOperationException.class,
                () -> orderService.findById(0L));
    }

    @Test
    void findAll() {
        assertThrowsExactly(UnsupportedOperationException.class,
                () -> orderService.findAll(Pageable.unpaged()));
    }

    @Test
    void update() {
        assertThrowsExactly(UnsupportedOperationException.class,
                () -> orderService.update(new OrderEntity()));
    }

    @Test
    void delete() {
        assertThrowsExactly(UnsupportedOperationException.class,
                () -> orderService.delete(0L));
    }

    @Test
    void findAllByCustomerId() {
        assertDoesNotThrow(() -> orderService.findAllByCustomerId(any(), Pageable.unpaged()));
    }

    @Test
    void getPopularTagInOrderByCustomerId() {
        assertDoesNotThrow(() -> orderService.getPopularTagInOrderByCustomerId(any()));
    }
}