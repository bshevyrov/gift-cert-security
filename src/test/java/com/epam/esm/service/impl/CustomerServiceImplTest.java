package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.repository.CustomerRepository;
import com.epam.esm.persistence.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService ;

    @Test
    void create() {
    }

    @Test
    void update() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                ()->customerService.update(new CustomerEntity()));
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                ()->customerService.delete(0L));
    }

    @Test
    void findAll() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void existsByUsername() {
    }
}