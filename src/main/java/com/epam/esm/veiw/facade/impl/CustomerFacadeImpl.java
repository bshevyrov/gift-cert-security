package com.epam.esm.veiw.facade.impl;

import com.epam.esm.mapper.CustomerMapper;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.service.CustomerService;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.facade.CustomerFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


/**
 * Class used for conversion  {@link CustomerEntity} and {@link CustomerDTO}.
 */
@Component
@RequiredArgsConstructor
public class CustomerFacadeImpl implements CustomerFacade {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    /**
     * Method consume customerDTO and return created customer.
     *
     * @param customerDTO dto object
     * @return {@link CustomerDTO}
     */
    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        return customerMapper.toDTO(customerService.create(customerMapper.toEntity(customerDTO)));
    }

    /**
     * Method consume id value and return dto object.
     *
     * @param id request parameter
     * @return {@link  CustomerDTO} created object
     */
    @Override
    public CustomerDTO findById(long id) {
        return customerMapper.toDTO(customerService.findById(id));
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public CustomerDTO update(CustomerDTO entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public void delete(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method produce {@Page} of dto object.
     *
     * @param pageable pagination object
     * @return page of dtos
     */
    @Override
    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<CustomerEntity> all = customerService.findAll(pageable);
        return new PageImpl<>(customerMapper.toDTOList(all.getContent()), pageable, all.getTotalElements());
    }

    /**
     * Method consume username value and return dto object.
     *
     * @param username request parameter
     * @return {@link  CustomerDTO} created object
     */
    @Override
    public CustomerDTO findByUsername(String username) {
        return customerMapper.toDTO(customerService.findByUsername(username));
    }
}


