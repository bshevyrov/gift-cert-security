//package com.epam.esm.persistence.repository;
//
//import com.epam.esm.persistence.entity.OrderEntity;
//import com.epam.esm.persistence.entity.OrderItemEntity;
//import com.epam.esm.util.CustomQuery;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
//    Page<OrderEntity> findAllByCustomerEntity_Id(Long customerId, Pageable pageable);
//
////    @Query("SELECT SUM(gc.price*oi.quantity) FROM GiftCertificateEntity gc  JOIN OrderItemEntity  oi ON gc.id= oi.giftCertificateEntity.id WHERE oi.orderEntity.id =?1")
////    Double getOrderCostByOrderId(Long orderId);
////
////    @Query("SELECT o.createTime FROM OrderEntity o WHERE o.id=?1")
////    LocalDateTime getCreateTimeByOrderId(Long orderId);
//
//    @Query(value = CustomQuery.GET_POPULAR_ORDER, nativeQuery = true)
//        Optional<OrderEntity> getPopularTagInOrderByCustomerId(Long id);
//}
