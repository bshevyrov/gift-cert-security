package com.epam.esm.service;

import com.epam.esm.persistence.entity.CustomerEntity;

public interface CustomerService extends BaseService<CustomerEntity> {
    CustomerEntity findByUsername(String username);

    boolean existsByUsername(String username);
}
