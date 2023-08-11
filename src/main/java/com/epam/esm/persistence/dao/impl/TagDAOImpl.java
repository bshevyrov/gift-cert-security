package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.TagDAO;
import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.utils.SortProp;
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
public class TagDAOImpl implements TagDAO {
    @PersistenceContext

    private EntityManager entityManager;


    @Override
    public TagEntity create(TagEntity tagEntity) {
        entityManager.persist(tagEntity);
        return tagEntity;
    }

    @Override
    @Deprecated
    public TagEntity update(TagEntity tagEntity) {

        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<TagEntity> findById(Long aLong) {
        return Optional.ofNullable(entityManager.find(TagEntity.class, aLong));
    }

    @Override
    public Optional<TagEntity> deleteById(Long aLong) {
        Optional<TagEntity> tagEntity = Optional.ofNullable(entityManager.find(TagEntity.class, aLong));
        if (tagEntity.isPresent()) {
            entityManager.remove(tagEntity);
        }
        return tagEntity;
    }


    @Override
    public List<TagEntity> findAll(Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TagEntity> criteriaQuery = criteriaBuilder.createQuery(TagEntity.class);
        Root<TagEntity> root = criteriaQuery.from(TagEntity.class);
        TypedQuery<TagEntity> query = entityManager.createQuery(criteriaQuery.select(root));
        List<SortProp> sortProp = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
            sortProp.add(new SortProp(
                    order.getProperty(), order.getDirection().name()));
        }
        if (!sortProp.isEmpty()) {
            List<Order> orderList = new ArrayList<>();

            for (SortProp prop : sortProp) {
                if (prop.getDirection().equals("DESC")) {
                    orderList.add(criteriaBuilder.desc(root.get(prop.getProperty())));
                } else {
                    orderList.add(criteriaBuilder.asc(root.get(prop.getProperty())));
                }
            }
            query = entityManager.createQuery(criteriaQuery.select(root).orderBy(orderList));

        }
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<TagEntity> list = query.getResultList();


//        String s = "select t from TagEntity t order by ? ASC";
//Query query = entityManager.createQuery(s);
//query.setFirstResult(10);
//     query           .setMaxResults(10);
//query.setParameter();
//
//
//        List<TagEntity> tags = entityManager.createQuery "select t.* from tag t order by :field ASC limit :pageSize offset :firstItem";
//
//                .setFirstResult(10)
//                .setMaxResults(10)
//                .getResultList();
        return list;
    }

}
