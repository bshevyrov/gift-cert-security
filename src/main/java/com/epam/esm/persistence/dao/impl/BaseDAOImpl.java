package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.BaseDAO;
import com.epam.esm.persistence.entity.Entity;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
/*
Used for CRUD operations with the database
 */
public abstract class BaseDAOImpl<E extends Entity> implements BaseDAO<E, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves entity in database.
     * @param e entity to save.
     * @return saved entity.
     */
    @Override
    public E create(E e) {
        return entityManager.merge(e);
    }

    /**
     * Updates entity in database.
     * @param e entity to update.
     * @return updated entity.
     */
    @Override
    public E update(E e) {
        return entityManager.merge(e);
    }

    /**
     * Finds entity in database by id.
     * @param eClass class associated with the corresponding table.
     * @param aLong id.
     * @return optional of found entity.
     */
    @Override
    public Optional<E> findById(Class<E> eClass, Long aLong) {
        return Optional.ofNullable(entityManager.find(eClass, aLong));
    }

    /**
     * Deletes entity from database by id.
     * @param eClass class associated with the corresponding table.
     * @param aLong id.
     * @return optional of deleted entity.
     */

    @Override
    public Optional<E> deleteById(Class<E> eClass, Long aLong) {
        Optional<E> e = Optional.ofNullable(entityManager.find(eClass, aLong));
        e.ifPresent(value -> {
            entityManager.remove(value);
            entityManager.flush();
        });
        return e;
    }

    /**
     * Finds all entity from database.
     * @param eClass class associated with the corresponding table.
     * @param pageable  pagination object.
     * @return page of entity.
     */
    @Override
    public Page<E> findAll(Class<E> eClass, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(eClass);
        Root<E> root = criteriaQuery.from(eClass);
        TypedQuery<E> query = entityManager.createQuery(criteriaQuery.select(root));

        if (!pageable.getSort().isEmpty()) {
            List<Order> orderList = getSortOrders(pageable, criteriaBuilder, root);
            query = entityManager.createQuery(criteriaQuery.select(root).orderBy(orderList));
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<E> list = query.getResultList();
        Long total = count(eClass);

        return new PageImpl<>(list, pageable, total);
    }

    /**
     * Creates list of sorting parameter for criteria API.
     * @param pageable  pagination object.
     * @param criteriaBuilder criteria builder.
     * @param root root query.
     * @return list of {@link Sort }
     */
     List<Order> getSortOrders(Pageable pageable, CriteriaBuilder criteriaBuilder, Root<E> root) {
        List<Order> orderList = new ArrayList<>();

        for (Sort.Order order : pageable.getSort()) {
            if (order.getDirection().name().equals("DESC")) {
                orderList.add(criteriaBuilder.desc(root.get(order.getProperty())));
            } else {
                orderList.add(criteriaBuilder.asc(root.get(order.getProperty())));
            }
        }
        return orderList;
    }

    /**
     * Checks if entity by id exists in database.
     * @param eClass class associated with the corresponding table.
     * @param entityId id.
     * @return true if exists or false if not exists,
     */

    @Override
    public Boolean existsById(Class<E> eClass, Long entityId) {
        return entityManager.unwrap(Session.class).createQuery(
                        "SELECT 1 FROM " + eClass.getSimpleName() + " WHERE EXISTS (SELECT 1 FROM " + eClass.getSimpleName() + " e WHERE e.id=:id)")
                .setParameter("id", entityId)
                .setMaxResults(1)
                .uniqueResult() != null;
    }

    /**
     *  Counts all entities of corresponding table.
     * @param eClass class associated with the corresponding table.
     * @return number of existing entities in the database.
     */
    @Override
    public Long count(Class<E> eClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(eClass)));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
