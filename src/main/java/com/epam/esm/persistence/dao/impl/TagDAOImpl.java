package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.TagDAO;
import com.epam.esm.persistence.entity.TagEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class TagDAOImpl extends CrudDAOImpl<TagEntity> implements TagDAO {
    @PersistenceContext
    private EntityManager entityManager;
//
//
//    @Override
//    public TagEntity create(TagEntity e) {
//        entityManager.merge(e);
//        return e;
//    }
//
//    @Override
//    @Deprecated
//    public TagEntity update(TagEntity e) {
//
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public Optional<TagEntity> findById(Long aLong) {
//        return Optional.ofNullable(entityManager.find(TagEntity.class, aLong));
//    }
//
//    @Override
//    public Optional<TagEntity> deleteById(Long aLong) {
//        Optional<TagEntity> e = Optional.ofNullable(entityManager.find(TagEntity.class, aLong));
//        if (e.isPresent()) {
//            entityManager.remove(e);
//        }
//        return e;
//    }
//
//
//    @Override
//    public Page<TagEntity> findAll(Pageable pageable) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<TagEntity> criteriaQuery = criteriaBuilder.createQuery(TagEntity.class);
//        Root<TagEntity> root = criteriaQuery.from(TagEntity.class);
//        TypedQuery<TagEntity> query = entityManager.createQuery(criteriaQuery.select(root));
//        List<SortProp> sortProp = new ArrayList<>();
//        for (Sort.Order order : pageable.getSort()) {
//            sortProp.add(new SortProp(
//                    order.getProperty(), order.getDirection().name()));
//        }
//        if (!sortProp.isEmpty()) {
//            List<Order> orderList = new ArrayList<>();
//
//            for (SortProp prop : sortProp) {
//                System.out.println(prop);
//                if (prop.getDirection().equals("DESC")) {
//                    orderList.add(criteriaBuilder.desc(root.get(prop.getProperty())));
//                } else {
//                    orderList.add(criteriaBuilder.asc(root.get(prop.getProperty())));
//                }
//            }
//            query = entityManager.createQuery(criteriaQuery.select(root).orderBy(orderList));
//
//        }
//        query.setFirstResult((int) pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        List<TagEntity> list = query.getResultList();
//
//
//        return new PageImpl<>(list, pageable, list.size());
//    }


    @Override
    public boolean existsByName(String tagTagEntityName) {
        return entityManager.unwrap(Session.class).createQuery(
                        "SELECT 1 FROM TagEntity WHERE EXISTS (SELECT 1 FROM TagEntity t WHERE t.name=:tagName)")
                .setParameter("tagName", tagTagEntityName)
                .setMaxResults(1)
                .uniqueResult() != null;
    }

    @Override
    public boolean existsById(Long tagTagEntityId) {
        return entityManager.unwrap(Session.class).createQuery(
                        "SELECT 1 FROM TagEntity WHERE EXISTS (SELECT 1 FROM TagEntity t WHERE t.id=:tagId)")
                .setParameter("tagId", tagTagEntityId)
                .setMaxResults(1)
                .uniqueResult() != null;
    }

    @Override
    public Optional<TagEntity> findByName(String tagEntityName) {
        return Optional.ofNullable(entityManager.createQuery("SELECT t FROM TagEntity t WHERE t.name=:tagName", TagEntity.class)
                .setParameter("tagName", tagEntityName)
                .getSingleResult());
    }
}
