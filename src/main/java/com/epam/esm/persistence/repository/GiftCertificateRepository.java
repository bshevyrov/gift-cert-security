//package com.epam.esm.persistence.repository;
//
//import com.epam.esm.persistence.entity.GiftCertificateEntity;
//import com.epam.esm.persistence.entity.TagEntity;
//import com.epam.esm.util.CustomQuery;
//import org.hibernate.annotations.Parameter;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Set;
//
//@Repository
//public interface GiftCertificateRepository extends PagingAndSortingRepository<GiftCertificateEntity, Long>, JpaSpecificationExecutor<GiftCertificateEntity> {
//Page<GiftCertificateEntity> findAll(Pageable pageable);
//    @Query(value = CustomQuery.FIND_ALL_GIFT_CERTIFICATE_BY_TAGS_ID_AND_SIZE,nativeQuery = true)
//    Page<GiftCertificateEntity> findAllByTags(@Param("tags") List<Long> tagEntities, @Param("tagCount") int tagEntitiesSize, Pageable pageable);
////    Page<GiftCertificateEntity> findAll(Example<GiftCertificateEntity> entityExample,Pageable pageable);
////    Page<GiftCertificateEntity> findAllByTagEntitiesIsContaining(List<TagEntity> tags,Pageable pageable);
//
//}
