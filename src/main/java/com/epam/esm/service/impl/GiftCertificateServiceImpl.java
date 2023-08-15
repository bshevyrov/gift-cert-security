package com.epam.esm.service.impl;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.exception.tag.TagNotFoundException;
import com.epam.esm.persistence.dao.GiftCertificateDAO;
import com.epam.esm.persistence.dao.TagDAO;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.UpdateRequestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Used  to manipulate GiftCertificate objects and collecting data.
 */
@Service

public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final MessageSource messageSource;
    private final GiftCertificateDAO giftCertificateDAO;
    private final TagDAO tagDAO;
    private final EntityManager entityManager;

    @Autowired

    public GiftCertificateServiceImpl(MessageSource messageSource, GiftCertificateDAO giftCertificateDAO, TagDAO tagDAO, EntityManager entityManager) {
        this.messageSource = messageSource;
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagDAO = tagDAO;
        this.entityManager = entityManager;
    }


    /**
     * Method creates gift certificate.
     * Checks if tag with this name exist
     * - if true add tag id to giftCertificate
     * - if false create new tag and add id of this tag to gift certificate
     *
     * @param giftCertificateEntity object for creation
     * @return id fon created object
     */
    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public GiftCertificateEntity create(GiftCertificateEntity giftCertificateEntity) {
     /*   long giftCertificateId = giftCertificateDAO.create(giftCertificate);
        giftCertificate.getTags().forEach(tag -> {
            if (!InputVerification.verifyName(tag.getName())) {
                throw new TagNameException(tag.getName());
            }
            if (tagDAO.existByName(tag.getName())) {
                giftCertificateTagDAO.create(new GiftCertificateTag(giftCertificateId, tagDAO.findByName(tag.getName()).getId()));
            } else {
                long tagId = tagDAO.create(new Tag(tag.getName()));
                giftCertificateTagDAO.create(new GiftCertificateTag(giftCertificateId, tagId));
            }
        });
        return giftCertificateId;*/
       /* if(!giftCertificate.getTags().isEmpty()){
            List<Tag> tags = giftCertificate.getTags();
            giftCertificate.setTags(new ArrayList<>());
            tags.forEach(tag -> {
                if (!InputVerification.verifyName(tag.getName())) {
                    throw new TagNameException(tag.getName());
                }
                if (!tagRepository.existsByName(tag.getName())) {
                    giftCertificate.getTags().add(tagRepository.save(tag));
                }
            });
        }*/
//        giftCertificateEntity.getTagEntities().forEach(tag -> {
//            if (!InputVerification.verifyName(tag.getName())) {
//                throw new TagNameException(tag.getName());
//            }
//        });
//        giftCertificateEntity.prepareTagsToInsert();
//        for (TagEntity tagEntity : giftCertificateEntity.getTagEntities()) {
//            giftCertificateEntity.addToTagThisGiftCertificate(tagEntity);
//        }

        List<TagEntity> tagEntities = giftCertificateEntity.getTagEntities();
        tagEntities.forEach(tagEntity -> {
            if(ObjectUtils.isEmpty(tagEntity.getGiftCertificateEntities())){
                tagEntity.setGiftCertificateEntities(new ArrayList<>());
            }
            tagEntity.getGiftCertificateEntities().add(giftCertificateEntity);
            Optional<TagEntity> byName = tagDAO.findByName(tagEntity.getName());
            byName.ifPresent(entity -> tagEntity.setId(entity.getId()));
        });

        return giftCertificateDAO.create(giftCertificateEntity);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void update(GiftCertificateEntity giftCertificateEntity) {
//        if (!InputVerification.verifyId(giftCertificate.getId())) {
//            throw new GiftCertificateIdException(giftCertificate.getId());
//        }
//        if (!giftCertificateDAO.existById(giftCertificate.getId())) {
//            throw new GiftCertificateNotFoundException(giftCertificate.getId());
//        }
//        if (giftCertificate.getTags() != null
//                && giftCertificate.getTags().size() > 0){
//            giftCertificateTagDAO.deleteByGiftCertificateId(giftCertificate.getId());
//            giftCertificate.getTags().forEach(tag -> {
//                if (!InputVerification.verifyName(tag.getName())) {
//                    throw new TagNameException(tag.getName());
//                }
//                if (tagDAO.existByName(tag.getName())) {
//                    giftCertificateTagDAO.create(new GiftCertificateTag(giftCertificate.getId(), tagDAO.findByName(tag.getName()).getId()));
//                } else {
//                    long tagId = tagDAO.create(new Tag(tag.getName()));
//                    giftCertificateTagDAO.create(new GiftCertificateTag(giftCertificate.getId(), tagId));
//                }
//            });
//        }
//        giftCertificate.setTags(null);
//        giftCertificateDAO.update(giftCertificate);
//        if (!InputVerification.verifyId(giftCertificateEntity.getId())) {
//            throw new GiftCertificateIdException(giftCertificateEntity.getId());
//        }

        GiftCertificateEntity toDB = giftCertificateDAO.findById(GiftCertificateEntity.class, giftCertificateEntity.getId())
                .orElseThrow(() -> new GiftCertificateNotFoundException(messageSource.getMessage("giftcertificate.notfound.exceptoion",
                        new Object[]{giftCertificateEntity.getId()},
                        LocaleContextHolder.getLocale())));
        UpdateRequestUtils.copyNotNullOrEmptyProperties(giftCertificateEntity, toDB);

//        List<TagEntity> tagEntities = toDB.getTagEntities();
//        for (TagEntity tagEntity : tagEntities) {
//            if(ObjectUtils.isEmpty(tagEntity.getGiftCertificateEntities())){
//                tagEntity.setGiftCertificateEntities(new ArrayList<>());
//            }
//            tagEntity.getGiftCertificateEntities().add(toDB);
//            Optional<TagEntity> byName = tagDAO.findByName(tagEntity.getName());
//            byName.ifPresent(entity -> tagEntity.setId(entity.getId()));
//        }
//        tagEntities.forEach(tagEntity -> {
//            if(ObjectUtils.isEmpty(tagEntity.getGiftCertificateEntities())){
//                tagEntity.setGiftCertificateEntities(new ArrayList<>());
//            }
//            tagEntity.getGiftCertificateEntities().add(toDB);
//            Optional<TagEntity> byName = tagDAO.findByName(tagEntity.getName());
//            byName.ifPresent(entity -> tagEntity.setId(entity.getId()));
//        });

//        giftCertificateService.update(giftCertificateMapper.toEntity(byId));
        create(toDB);
//        giftCertificateDAO.update(toDB);
    }

    /**
     * Method finds gift certificate by id
     * Checks if gift certificate exists:
     * - if true - finds gift certificate by id and set tags to it
     * - if false - throws {@link GiftCertificateNotFoundException} exception
     *
     * @param id requested parameter
     * @return {@link  GiftCertificateEntity} found object
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public GiftCertificateEntity findById(long id) {
//        if (!InputVerification.verifyId(id)) {
//            throw new GiftCertificateIdException(id);
//        }
        return giftCertificateDAO.findById(GiftCertificateEntity.class, id).orElseThrow(() ->
                new GiftCertificateNotFoundException(messageSource.getMessage("giftcertificate.notfound.exceptoion",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
//        if (!InputVerification.verifyId(id)) {
//            throw new GiftCertificateIdException(id);
//        }
//        if (!giftCertificateDAO.existById(id)) {
//            throw new GiftCertificateNotFoundException(id);
//
//        }
//        GiftCertificate giftCertificate = giftCertificateDAO.findById(id);
//        giftCertificate.setTags((Set<Tag>) tagDAO.findAllByGiftCertificateId(giftCertificate.getId()));
//        return giftCertificate;
    }

    /**
     * Method finds all gift certificates based on search param, add tags for each one, and sort items.
     *
     * @param pageable entity
     * @return List of objects
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public Page<GiftCertificateEntity> findAll(Pageable pageable) {
        /*List<GiftCertificate> giftCertificateList = giftCertificateRepository.findAll(searchRequest);
        giftCertificateList.forEach(giftCertificate -> giftCertificate.setTags((Set<Tag>) tagDAO.findAllByGiftCertificateId(giftCertificate.getId())));
        return giftCertificateList;*/
//        Pageable pageable = PageRequest.of(0,2);
        return giftCertificateDAO.findAll(GiftCertificateEntity.class, pageable);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)

    public Page<GiftCertificateEntity> findAllByTagsName(List<TagEntity> tags, Pageable pageable) {
        return giftCertificateDAO.findAllByTagsId(findTagsIdByName(tags), pageable);
    }


    private List<Long> findTagsIdByName(List<TagEntity> tags) {
        List<Long> currentTags = new ArrayList<>();
        tags.forEach(tagEntity ->
                currentTags.add(tagDAO.findByName(tagEntity.getName()).orElseThrow(() ->
                        new TagNotFoundException(messageSource.getMessage("tag.notfound.exception",
                                new Object[]{tagEntity.getName()},
                                LocaleContextHolder.getLocale()))).getId()));
        return currentTags;
    }
//    private List<TagEntity> findTagsByName(List<TagEntity> tags) {
//        List<TagEntity> currentTags = new ArrayList<>();
//        tags.forEach(tagEntity ->
//                currentTags.add(tagRepository.findByName(tagEntity.getName()).orElseThrow(() ->
//                        new TagNotFoundException(messageSource.getMessage("tag.notfound.exception",
//                                new Object[]{tagEntity.getName()},
//                                LocaleContextHolder.getLocale())))));
//        return currentTags;
//    }

    /**
     * Method updates gift certificate.
     * Checks if giftCertificate  if id valid:
     * - if true proceed to next operation
     * Checks if giftCertificate  is exists by id:
     * - if true proceed to next operation
     * - if false throws GiftCertificateNotFoundException exception
     * Then checks if tags set is null or empty:
     * - if true - doesn't  proceed to update tags
     * - if false - removes all connection with  tags,  and checks is tag name correct
     * - if false throws TagNameException
     * - if true  checks if tags already exist
     * - if true adds tag id to giftCertificate and creates relation between tag and giftCertificate
     * - if false creates new tag and adds tag id to giftCertificate, creates relation between tag and giftCertificate
     *
     * @param giftCertificateEntity candidate for update
     */


    /**
     * Method deletes gift certificate
     * <p>
     * Checks if gift certificate exists by id:
     * - if true - deletes from database
     * - if false - throws GiftCertificateNotFoundException exception
     *
     * @param id requested parameter
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})

    public GiftCertificateEntity delete(long id) {

        return giftCertificateDAO.deleteById(GiftCertificateEntity.class, id).orElseThrow(() ->
                new GiftCertificateNotFoundException(messageSource.getMessage("giftcertificate.notfound.exceptoion",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }
}
