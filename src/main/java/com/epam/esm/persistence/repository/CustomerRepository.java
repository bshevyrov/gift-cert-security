package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
