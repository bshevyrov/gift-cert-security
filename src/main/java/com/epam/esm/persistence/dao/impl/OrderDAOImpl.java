package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.OrderDAO;
import com.epam.esm.persistence.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl extends BaseDAOImpl<OrderEntity> implements OrderDAO {
    @PersistenceContext
    EntityManager entityManager;

    public static final String GET_POPULAR_ORDER=
            "SELECT distinct o.* " +
                    "from `order` o " +
                    "join order_item oi on o.id = oi.order_id\n" +
                    "    join gift_certificate_tag gct on oi.gift_certificate_id = gct.gift_certificate_id\n" +
                    "join (SELECT  gct.tag_id\n" +
                    "       FROM gift_certificate_tag gct\n" +
                    "                join order_item oi on gct.gift_certificate_id = oi.gift_certificate_id\n" +
                    "                join `order` o on o.id = oi.order_id\n" +
                    "       WHERE customer_id = :customerId\n" +
                    "       GROUP BY gct.tag_id\n" +
                    "       ORDER BY count(*) DESC\n" +
                    "       limit 1) sub On gct.tag_id = sub.tag_id\n" +
                    "WHERE customer_id =:customerId\n" +
                    "ORDER BY o.cost DESC\n" +
                    "limit 1";

    /**
     * Finds all orders by customer id.
     * @param id long id.
     * @param pageable  pagination object.
     * @return {@link Page} of {@link OrderEntity}
     */
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

    /**
     * Counts all customer orders by id.
     * @param id long identifier.
     * @return number of existing entities in the database.
     */
    private Long countByCustomerId(Long id) {
        Query query = entityManager.createQuery("Select count(o) from OrderEntity o where o.customerEntity.id=:customerId");
        query.setParameter("customerId", id);
        return (Long) query.getSingleResult();
    }

    /**
     * Returns the order with the highest value with the most popular tag.
     * @param id long identifier.
     * @return optional of found order.
     */
    @Override
    public Optional<OrderEntity> getPopularTagInOrderByCustomerId(Long id) {
        Query query = entityManager.createNativeQuery(GET_POPULAR_ORDER, OrderEntity.class);
        query.setParameter("customerId", id);
        OrderEntity result = (OrderEntity) query.getSingleResult();
        return Optional.ofNullable(result);
    }
}
