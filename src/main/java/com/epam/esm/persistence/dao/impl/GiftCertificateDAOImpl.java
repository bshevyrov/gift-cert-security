package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.GiftCertificateDAO;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class GiftCertificateDAOImpl extends BaseDAOImpl<GiftCertificateEntity> implements GiftCertificateDAO {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Finds all tags with corresponding tags.
     * @param tagsId long id.
     * @param pageable  pagination object.
     * @return {@link Page} of {@link GiftCertificateEntity}
     */
    @Override
    public Page<GiftCertificateEntity> findAllByTagsId(List<Long> tagsId, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificateEntity> criteriaQuery = criteriaBuilder.createQuery(GiftCertificateEntity.class);

        Root<GiftCertificateEntity> root = criteriaQuery.from(GiftCertificateEntity.class);
        Join<GiftCertificateEntity, TagEntity> gctJoin = root.join("tagEntities");

        Predicate tagIdPredicate = gctJoin.get("id").in(tagsId);
        Predicate tagsCountPredicate = criteriaBuilder.equal(criteriaBuilder.count(root.get("id")), tagsId.size());

        criteriaQuery.select(root)
                .groupBy(root.get("id"))
                .where(tagIdPredicate)
                .having(tagsCountPredicate);
        TypedQuery<GiftCertificateEntity> query = entityManager.createQuery(criteriaQuery);

        if (!pageable.getSort().isEmpty()) {
            List<Order> orderList = getSortOrders(pageable, criteriaBuilder, root);
            query = entityManager.createQuery(criteriaQuery.orderBy(orderList));
        }
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<GiftCertificateEntity> resultList = query.getResultList();
        Long count = count(GiftCertificateEntity.class);
        return new PageImpl<>(resultList, pageable, count);
    }
}

