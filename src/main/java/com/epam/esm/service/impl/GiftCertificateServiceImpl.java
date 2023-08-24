package com.epam.esm.service.impl;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.persistence.dao.GiftCertificateDAO;
import com.epam.esm.persistence.dao.TagDAO;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
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
    private final TagService tagService;

    @Autowired

    public GiftCertificateServiceImpl(MessageSource messageSource, GiftCertificateDAO giftCertificateDAO, TagDAO tagDAO, EntityManager entityManager, TagService tagService) {
        this.messageSource = messageSource;
        this.giftCertificateDAO = giftCertificateDAO;
        this.tagDAO = tagDAO;
        this.tagService = tagService;
    }


    /**
     * Method creates gift certificate.
     * Creates a new tag if it doesn't exist.
     *
     * @param giftCertificateEntity object for creation
     * @return id of created object
     */
    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public GiftCertificateEntity create(GiftCertificateEntity giftCertificateEntity) {
        List<TagEntity> tagEntities = giftCertificateEntity.getTagEntities();
        tagEntities.forEach(tagEntity -> {
            if (ObjectUtils.isEmpty(tagEntity.getGiftCertificateEntities())) {
                tagEntity.setGiftCertificateEntities(new ArrayList<>());
            }
            tagEntity.getGiftCertificateEntities().add(giftCertificateEntity);
            Optional<TagEntity> byName = tagDAO.findByName(tagEntity.getName());
            byName.ifPresent(entity -> tagEntity.setId(entity.getId()));
        });
        return giftCertificateDAO.create(giftCertificateEntity);
    }

    /**
     * Updates entity in database.
     * Throws exception if GiftCertificate doesn't exist.
     *
     * @param giftCertificateEntity
     * @return created entity
     * @throws {@link GiftCertificateNotFoundException}
     */
    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public GiftCertificateEntity update(GiftCertificateEntity giftCertificateEntity) {
        GiftCertificateEntity toDB = giftCertificateDAO.findById(GiftCertificateEntity.class, giftCertificateEntity.getId())
                .orElseThrow(() -> new GiftCertificateNotFoundException(messageSource.getMessage("gift.certificate.notfound.exception",
                        new Object[]{giftCertificateEntity.getId()},
                        LocaleContextHolder.getLocale())));
        UpdateRequestUtils.copyNotNullOrEmptyProperties(giftCertificateEntity, toDB);
        return create(toDB);
    }

    /**
     * Method finds gift certificate by id.
     * <p>
     * If gift certificate doesn't exist throw exception.
     *
     * @param id requested parameter
     * @return {@link  GiftCertificateEntity} found object
     * @throws {@link GiftCertificateNotFoundException}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public GiftCertificateEntity findById(long id) {
        return giftCertificateDAO.findById(GiftCertificateEntity.class, id).orElseThrow(() ->
                new GiftCertificateNotFoundException(messageSource.getMessage("gift.certificate.notfound.exception",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

    /**
     * Method finds all gift certificates.
     *
     * @param pageable pagination object
     * @return {@list Page} of objects
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public Page<GiftCertificateEntity> findAll(Pageable pageable) {
        return giftCertificateDAO.findAll(GiftCertificateEntity.class, pageable);
    }

    /**
     * Method finds all gift certificates based on tags name.
     *
     * @param tags     List of {@link TagEntity}
     * @param pageable pagination object
     * @return {@link Page} of {@link GiftCertificateEntity}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public Page<GiftCertificateEntity> findAllByTagsName(List<TagEntity> tags, Pageable pageable) {
        ArrayList<Long> list = new ArrayList<>();
        tags.forEach(tagEntity -> list.add(tagService.findTagIdByName(tagEntity.getName())));
        return giftCertificateDAO.findAllByTagsId(list, pageable);
    }


    /**
     * Method deletes gift certificate
     * <p>
     * Checks if gift certificate exists by id.
     * Throws exception if it doesn't exist.
     *
     * @param id requested parameter
     * @return found entity
     * @throws {@link GiftCertificateNotFoundException}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})

    public GiftCertificateEntity delete(long id) {
        return giftCertificateDAO.deleteById(GiftCertificateEntity.class, id).orElseThrow(() ->
                new GiftCertificateNotFoundException(messageSource.getMessage("gift.certificate.notfound.exceptoion",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }
}
