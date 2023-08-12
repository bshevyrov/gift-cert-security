package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.entity.OrderEntity;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends CrudDAOImpl<OrderEntity> implements OrderDAO {
}
