package com.epam.esm.service;

import com.epam.esm.persistence.entity.OrderItemEntity;

import java.util.List;

public interface OrderItemService extends BaseService<OrderItemEntity> {
  List<OrderItemEntity>  createAll(List<OrderItemEntity> orderItemEntities);

}
