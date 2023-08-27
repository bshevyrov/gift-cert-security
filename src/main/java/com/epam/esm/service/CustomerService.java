package com.epam.esm.service;

import com.epam.esm.persistence.entity.CustomerEntity;

public interface CustomerService extends BaseService<CustomerEntity> {
    CustomerEntity register(CustomerEntity customerEntity);

    CustomerEntity findByUsername(String username);

}
