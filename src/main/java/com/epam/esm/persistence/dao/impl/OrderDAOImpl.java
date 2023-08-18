package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.entity.CustomerEntity_;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderEntity_;
import com.epam.esm.util.CustomQuery;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl extends BaseDAOImpl<OrderEntity> implements OrderDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<OrderEntity> findAllByCustomerEntityId(Long id, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteriaQuery = criteriaBuilder.createQuery(OrderEntity.class);
        Root<OrderEntity> orderEntityRoot = criteriaQuery.from(OrderEntity.class);
        Join<OrderEntity, CustomerEntity> customerEntityJoin = orderEntityRoot.join(OrderEntity_.customerEntity);

        criteriaQuery.where(criteriaBuilder.equal(customerEntityJoin.get(CustomerEntity_.id), id));

        TypedQuery<OrderEntity> query = entityManager.createQuery(criteriaQuery);

        if (!pageable.getSort().isEmpty()) {
            List<Order> orderList = getSortOrders(pageable, criteriaBuilder, orderEntityRoot);
            query = entityManager.createQuery(criteriaQuery.orderBy(orderList));
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<OrderEntity> resultList = query.getResultList();
        Long count = countByCustomerId(id);
        return new PageImpl<>(resultList, pageable, count);
    }

    private Long countByCustomerId(Long id) {
        Query query = entityManager.createQuery("Select count(o) from OrderEntity o where o.customerEntity.id=:customerId");
        query.setParameter("customerId", id);
        return (Long) query.getSingleResult();
    }

    @Override
    public Optional<OrderEntity> getPopularTagInOrderByCustomerId(Long id) {
        Query query = entityManager.createNativeQuery(CustomQuery.GET_POPULAR_ORDER, OrderEntity.class);
        query.setParameter("customerId", id);
        OrderEntity result = (OrderEntity) query.getSingleResult();
        return Optional.ofNullable(result);
    }
}
