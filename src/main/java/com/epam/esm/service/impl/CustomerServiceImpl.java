package com.epam.esm.service.impl;

import com.epam.esm.entity.Customer;
import com.epam.esm.exception.giftcertificate.GiftCertificateIdException;
import com.epam.esm.exception.tag.TagNotFoundException;
import com.epam.esm.repository.CustomerRepository;
import com.epam.esm.service.CustomerService;
import com.epam.esm.util.InputVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
@Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer entity) {
        return null;
    }

    @Override
    public Customer findById(long id) {

        if(!InputVerification.verifyId(id)){
            throw new CustomerIdException(id);
        }
        return customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void update(Customer entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
