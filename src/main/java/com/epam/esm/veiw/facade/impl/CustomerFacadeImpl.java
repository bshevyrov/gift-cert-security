package com.epam.esm.veiw.facade.impl;

import com.epam.esm.mapper.CustomerMapper;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.service.CustomerService;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.facade.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component

public class CustomerFacadeImpl implements CustomerFacade {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerFacadeImpl(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO create(CustomerDTO entity) {
        return customerMapper.toDTO(customerService.create(customerMapper.toEntity(entity)));
    }

    @Override
    public CustomerDTO findById(long id) {
        return customerMapper.toDTO(customerService.findById(id));
    }


    @Override
    @Deprecated
    public void update(CustomerDTO entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<CustomerEntity> all = customerService.findAll(pageable);
        return new PageImpl<>(customerMapper.toDTOList(all.getContent()), pageable, all.getTotalElements());
    }
}
