package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.GiftCertificateDAO;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.GiftCertificateTagEntity;
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

@Repository
public class GiftCertificateDAOImpl extends BaseDAOImpl<GiftCertificateEntity> implements GiftCertificateDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<GiftCertificateEntity> findAllByTagsId(List<Long> tagsId, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificateEntity> criteriaQuery = criteriaBuilder.createQuery(GiftCertificateEntity.class);
        Root<GiftCertificateEntity> root = criteriaQuery.from(GiftCertificateEntity.class);

        Join<GiftCertificateEntity, GiftCertificateTagEntity> joinTag = root.join("tags");
        Predicate tagIdPredicate = joinTag.get("tag").get("id").in(tagsId);


        criteriaQuery.groupBy(root.get("id"));
        criteriaQuery.having(criteriaBuilder.equal(criteriaBuilder.count(root.get("id")), tagsId.size()));
        criteriaQuery.where(tagIdPredicate);


        TypedQuery<GiftCertificateEntity> query = entityManager.createQuery(criteriaQuery);

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



        List<GiftCertificateEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return new PageImpl<>(resultList, pageable, resultList.size());
    }


}

