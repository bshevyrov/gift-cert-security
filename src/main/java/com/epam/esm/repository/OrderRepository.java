package com.epam.esm.repository;

import com.epam.esm.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    Page<Order> findAllByCustomer_Id(Long customerId, Pageable pageable);

    @Query("SELECT SUM(gc.price*oi.quantity) FROM GiftCertificate gc  JOIN OrderItem  oi ON gc.id= oi.giftCertificate.id WHERE oi.order.id =?1")
    Double getOrderCostByOrderId(Long orderId);

    @Query("SELECT o.createTime FROM Order o WHERE o.id=?1")
    LocalDateTime getCreateTimeByOrderId(Long orderId);
}
