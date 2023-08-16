package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.entity.CustomerEntity_;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.persistence.entity.OrderEntity_;
import com.epam.esm.util.CustomQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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
//        Join<OrderEntity,O>
        Join<OrderEntity, CustomerEntity> customerEntityJoin = orderEntityRoot.join(OrderEntity_.customerEntity);

        criteriaQuery.where(criteriaBuilder.equal(customerEntityJoin.get(CustomerEntity_.id), id));

        TypedQuery<OrderEntity> query = entityManager.createQuery(criteriaQuery);
//        String qlString = "select o from OrderEntity o where o.customerEntity.id=:customerId";
//        TypedQuery< OrderEntity > query = entityManager.createQuery(qlString, OrderEntity.class);

        if (!pageable.getSort().isEmpty()) {
            List<Order> orderList = new ArrayList<>();

            for (Sort.Order order : pageable.getSort()) {
                if (order.getDirection().name().equals("DESC")) {
                    orderList.add(criteriaBuilder.desc(orderEntityRoot.get(order.getProperty())));
                } else {
                    orderList.add(criteriaBuilder.asc(orderEntityRoot.get(order.getProperty())));
                }
            }
            query = entityManager.createQuery(criteriaQuery.orderBy(orderList));
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());


        List<OrderEntity> resultList = query.getResultList();
        Long count = count(OrderEntity.class);
        return new PageImpl<>(resultList, pageable, count);

    }

    @Override
    public Optional<OrderEntity> getPopularTagInOrderByCustomerId(Long id) {

        return Optional.ofNullable(entityManager.createQuery(CustomQuery.GET_POPULAR_ORDER, OrderEntity.class)
                .setParameter("customerId", id)
                .getSingleResult());
    }
}
