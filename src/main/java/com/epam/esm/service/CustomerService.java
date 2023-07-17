package com.epam.esm.service;

import com.epam.esm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends BaseService<Customer> {
    Page<Customer> findAll(Pageable pageable);
}
