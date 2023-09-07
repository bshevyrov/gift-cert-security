package com.epam.esm.service.impl;

import com.epam.esm.exception.customer.CustomerNotFoundException;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.entity.RoleEntity;
import com.epam.esm.persistence.entity.type.Status;
import com.epam.esm.persistence.repository.CustomerRepository;
import com.epam.esm.persistence.repository.RoleRepository;
import com.epam.esm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Used  to manipulate Customer objects and collecting data.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final MessageSource messageSource;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;


    /**
     * Method adds default role ROLE_USER and sets status ACTIVE to  {@link CustomerEntity}.
     * <p>
     * Password encrypts with {@link BCryptPasswordEncoder}. Then saves in database.Can throw {@link RuntimeException} if role doesn`t exist
     *
     * @param customerEntity object for creation.
     * @return created object.
     */

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CustomerEntity create(CustomerEntity customerEntity) {
        RoleEntity roleEntity = roleRepository.findByName(RoleEntity.ROLE_USER.getName())
                .orElseThrow(() -> new RuntimeException("Role not found."));
        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(roleEntity);
        customerEntity.setPassword(new BCryptPasswordEncoder().encode(customerEntity.getPassword()));
        customerEntity.setRoleEntities(roleEntities);
        customerEntity.setStatus(Status.ACTIVE);
        return customerRepository.save(customerEntity);
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
     * Finds customer by id. Can throw {@link CustomerNotFoundException} if not found, or {@link ResponseStatusException} if not authorized.
     *
     * @param id requested parameter
     * @return {@link CustomerEntity} found object.
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public CustomerEntity findById(long id) {
        if (!isAuthenticatedUser(id)) {
            throw new AccessDeniedException(messageSource.getMessage(
                    "access.denied.exception",
                    new Object[]{id},
                    LocaleContextHolder.getLocale()));
        }
        return customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(
                        messageSource.getMessage("customer.notfound.exception",
                                new Object[]{"id - " + id},
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
     *
     * @param pageable pagination object
     * @return {@link Page} of {@link CustomerEntity}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public Page<CustomerEntity> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    /**
     * Finds customer by username or throws  {@link CustomerNotFoundException} if customer doesn`t exists.
     *
     * @param username requested parameter
     * @return {@link CustomerEntity} found object.
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public CustomerEntity findByUsername(String username) {
        return customerRepository.findByUsername(username).orElseThrow(() ->
                new CustomerNotFoundException(
                        messageSource.getMessage("customer.notfound.exception",
                                new Object[]{"username - " + username},
                                LocaleContextHolder.getLocale())));
    }

    /**
     * Checks if customer exist by username/
     *
     * @param username request value
     * @return true if exists
     */
    @Override
    public boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }
}
