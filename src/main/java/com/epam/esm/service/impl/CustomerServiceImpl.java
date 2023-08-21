package com.epam.esm.service.impl;

import com.epam.esm.exception.customer.CustomerNotFoundException;
import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.persistence.dao.CustomerDAO;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Used  to manipulate Customer objects and collecting data.
 */
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

    /**
     * Method creates CustomerEntity.
     * @param entity object for creation.
     * @return created object.
     */

    @Override
    public CustomerEntity create(CustomerEntity entity) {
        return customerDAO.create(entity);
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public CustomerEntity update(CustomerEntity entity) {
        throw new UnsupportedOperationException();
    }


    /**
     * Method finds gift certificate by id.
     * <p>
     * If gift certificate doesn't exist throw exception.
     *
     * @param id requested parameter
     * @return {@link  GiftCertificateEntity} found object
     * @throws {@link GiftCertificateNotFoundException}



    /**
     * Finds customer by id.
     * @param id requested parameter
     * @return {@link CustomerEntity} found object.
     * @throws {@link CustomerNotFoundException}
     */
    @Override
    public CustomerEntity findById(long id) {
        return customerDAO.findById(CustomerEntity.class, id).orElseThrow(
                () -> new CustomerNotFoundException(
                        messageSource.getMessage("customer.notfound.exception",
                                new Object[]{id},
                                LocaleContextHolder.getLocale())));
    }
    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public CustomerEntity delete(long id) {
        throw new UnsupportedOperationException();
    }
    /**
     * Method finds all customers with pagination.
     * @param pageable pagination object
     * @return {@link Page} of {@link CustomerEntity}
     */
    @Override
    public Page<CustomerEntity> findAll(Pageable pageable) {
        return customerDAO.findAll(CustomerEntity.class, pageable);
    }
}
