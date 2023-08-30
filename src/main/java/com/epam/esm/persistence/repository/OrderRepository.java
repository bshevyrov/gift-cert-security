package com.epam.esm.persistence.repository;

import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.util.CustomQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerEntity_Id(Long customerId, Pageable pageable);

    @Query(value = CustomQuery.GET_POPULAR_ORDER, nativeQuery = true)
    Optional<OrderEntity> getPopularTagInOrderByCustomerId(Long id);
}
