package com.epam.esm.service;

import com.epam.esm.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends BaseService<CustomerEntity> {
    Page<CustomerEntity> findAll(Pageable pageable);
}
