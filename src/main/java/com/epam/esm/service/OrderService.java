package com.epam.esm.service;

import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderItemEntity;
import com.epam.esm.veiw.dto.OrderItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService extends BaseService<OrderEntity>{
  Page<OrderEntity> findAllByCustomerId(Long id, Pageable pageable);
  OrderEntity getPopularTagInOrderByCustomerId(Long id);

    OrderEntity createOrderByOrderItems(List<OrderItemEntity> toEntityList);
}
