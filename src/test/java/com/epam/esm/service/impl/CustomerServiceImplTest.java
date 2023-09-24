package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.CustomerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {


    @Mock
    private CustomerServiceImpl customerService;

    @Test
    void create() {
        assertDoesNotThrow(() -> customerService.create(new CustomerEntity()));
    }

    @Test
    void update() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                () -> customerService.update(new CustomerEntity()));
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> customerService.findById(anyLong()));
    }

    @Test
    void delete() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                () -> customerService.delete(0L));
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> customerService.findAll(Pageable.unpaged()));
    }

    @Test
    void findByUsername() {
        assertDoesNotThrow(() -> customerService.findByUsername(anyString()));
    }

    @Test
    void existsByUsername() {
        assertDoesNotThrow(() -> customerService.existsByUsername(anyString()));
    }
}