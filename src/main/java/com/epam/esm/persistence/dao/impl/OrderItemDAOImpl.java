package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.OrderItemDAO;
import com.epam.esm.persistence.entity.OrderItemEntity;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDAOImpl extends BaseDAOImpl<OrderItemEntity> implements OrderItemDAO {
}
