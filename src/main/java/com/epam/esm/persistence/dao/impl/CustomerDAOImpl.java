package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.CustomerDAO;
import com.epam.esm.persistence.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl extends CrudDAOImpl<CustomerEntity> implements CustomerDAO {
}
