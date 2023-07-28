package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.entity.CustomerEntity;
import com.epam.esm.exception.customer.CustomerIdException;
import com.epam.esm.exception.customer.CustomerNotFoundException;
import com.epam.esm.persistence.repository.CustomerRepository;
import com.epam.esm.service.CustomerService;
import com.epam.esm.util.InputVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final MessageSource messageSource;
    private final CustomerRepository customerRepository;
@Autowired
    public CustomerServiceImpl(MessageSource messageSource, CustomerRepository customerRepository) {
    this.messageSource = messageSource;
    this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity create(CustomerEntity entity) {
        return null;
    }

    @Override
    public CustomerEntity findById(long id) {

        if(!InputVerification.verifyId(id)){
            throw new CustomerIdException(id);
        }
        return customerRepository.findById(id).orElseThrow(
                ()-> new CustomerNotFoundException(
                        messageSource.getMessage("customer.notfound.exception",
                                new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    @Override
    public List<CustomerEntity> findAll() {
        return null;
    }

    @Override
    public void update(CustomerEntity entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Page<CustomerEntity> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
