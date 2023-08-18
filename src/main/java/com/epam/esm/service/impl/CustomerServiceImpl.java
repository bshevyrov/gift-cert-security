package com.epam.esm.service.impl;

import com.epam.esm.exception.customer.CustomerNotFoundException;
import com.epam.esm.persistence.dao.CustomerDAO;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final MessageSource messageSource;
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(MessageSource messageSource, CustomerDAO customerDAO) {
        this.messageSource = messageSource;
        this.customerDAO = customerDAO;
    }


    @Override
    public CustomerEntity create(CustomerEntity entity) {
        return customerDAO.create(entity);
    }


    @Override
    @Deprecated
    public CustomerEntity update(CustomerEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CustomerEntity findById(long id) {
        return customerDAO.findById(CustomerEntity.class, id).orElseThrow(
                () -> new CustomerNotFoundException(
                        messageSource.getMessage("customer.notfound.exception",
                                new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }

    @Override
    @Deprecated
    public CustomerEntity delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<CustomerEntity> findAll(Pageable pageable) {
        return customerDAO.findAll(CustomerEntity.class, pageable);
    }
}
