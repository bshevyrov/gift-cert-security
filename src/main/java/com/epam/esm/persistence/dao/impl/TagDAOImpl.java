package com.epam.esm.persistence.dao.impl;

import com.epam.esm.persistence.dao.TagDAO;
import com.epam.esm.persistence.entity.TagEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class TagDAOImpl extends BaseDAOImpl<TagEntity> implements TagDAO {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Checks if tag exists in database.
     *
     * @param tagTagEntityName string name parameter.
     * @return true is exists.
     */
    @Override
    public boolean existsByName(String tagTagEntityName) {
        return entityManager.unwrap(Session.class).createQuery(
                        "SELECT 1 FROM TagEntity WHERE EXISTS (SELECT 1 FROM TagEntity t WHERE t.name=:tagName)")
                .setParameter("tagName", tagTagEntityName)
                .setMaxResults(1)
                .uniqueResult() != null;
    }

    /**
     * Finds {@link TagEntity} in database by name.
     *
     * @param tagEntityName string parameter.
     * @return optional of found entity.
     */
    @Override
    public Optional<TagEntity> findByName(String tagEntityName) {
        return entityManager.createQuery("SELECT t FROM TagEntity t WHERE t.name=:tagName", TagEntity.class)
                .setParameter("tagName", tagEntityName)
                .getResultList().stream().findFirst();
    }
}
