//package com.epam.esm.persistence.repository;
//
//import com.epam.esm.persistence.entity.TagEntity;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface TagRepository extends PagingAndSortingRepository<TagEntity, Long> {
//    boolean existsByName(String name);
//    Optional<TagEntity> findByName(String name);
////    @Query(value = "SELECT gc.tagEntities FROM  GiftCertificateEntity gc WHERE gc.id=?1")
////    Page<TagEntity> findAllByGiftCertificateEntityId(Long id, Pageable pageable);
//}
