package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.entity.OrderEntity;
import com.epam.esm.util.CustomQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl extends CrudDAOImpl<OrderEntity> implements OrderDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<OrderEntity> findAllByCustomerEntityId(Long id, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> criteriaQuery = criteriaBuilder.createQuery(OrderEntity.class);
        Root<OrderEntity> root = criteriaQuery.from(OrderEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("customer_id"), id));
        TypedQuery<OrderEntity> query = entityManager.createQuery(criteriaQuery);
//        String qlString = "select o from OrderEntity o where o.customerEntity.id=:customerId";
//        TypedQuery< OrderEntity > query = entityManager.createQuery(qlString, OrderEntity.class);

        if (!pageable.getSort().isEmpty()) {
            List<Order> orderList = new ArrayList<>();

            for (Sort.Order order : pageable.getSort()) {
                if (order.getDirection().name().equals("DESC")) {
                    orderList.add(criteriaBuilder.desc(root.get(order.getProperty())));
                } else {
                    orderList.add(criteriaBuilder.asc(root.get(order.getProperty())));
                }
            }
            query = entityManager.createQuery(criteriaQuery.orderBy(orderList));
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());


        List<OrderEntity> resultList = query.getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());

    }

    @Override
    public Optional<OrderEntity> getPopularTagInOrderByCustomerId(Long id) {

        return Optional.ofNullable(entityManager.createQuery(CustomQuery.GET_POPULAR_ORDER, OrderEntity.class)
                .setParameter("customerId", id)
                .getSingleResult());
    }
}
