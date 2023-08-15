package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.AbstractEntity;
import com.epam.esm.persistence.entity.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudDAO<E extends Entity, ID extends Number> {
    E create(E e);

    E update(E e);

    Optional<E> findById(Class<E> eClass,ID id);

    Optional<E> deleteById(Class<E> eClass,ID id);

    Page<E> findAll(Class<E> eClass,Pageable pageable);
    Boolean existsById(Class<E> e,ID id);

    //    @Override
//    public boolean existsByName(Class<Entity> aClass,String entityName) {
//        return entityManager.unwrap(Session.class).createQuery(
//                        "SELECT 1 FROM "+aClass.getSimpleName()+" WHERE EXISTS (SELECT 1 FROM "+aClass.getSimpleName()+" e WHERE e.name=:name)")
//                .setParameter("name", entityName)
//                .setMaxResults(1)
//                .uniqueResult() != null;
//    }
//

}
