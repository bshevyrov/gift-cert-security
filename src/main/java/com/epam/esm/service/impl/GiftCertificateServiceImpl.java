package com.epam.esm.service.impl;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.persistence.repository.GiftCertificateRepository;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.util.UpdateRequestUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Used  to manipulate GiftCertificate objects and collecting data.
 */
@Service
@RequiredArgsConstructor
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final MessageSource messageSource;
    private final GiftCertificateRepository giftCertificateRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;

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
            Optional<TagEntity> tagsFoundByName = tagRepository.findByName(tagEntity.getName());
            tagsFoundByName.ifPresent(entity -> tagEntity.setId(entity.getId()));
        });
        return giftCertificateRepository.save(giftCertificateEntity);
    }

    /**
     * Updates entity in database.
     * Throws {@link GiftCertificateNotFoundException} if GiftCertificate doesn't exist.
     *
     * @param giftCertificateEntity request entity
     * @return created entity
     */
    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public GiftCertificateEntity update(GiftCertificateEntity giftCertificateEntity) {
        GiftCertificateEntity giftCertificateFoundById = giftCertificateRepository.findById(giftCertificateEntity.getId())
                .orElseThrow(getGiftCertificateNotFoundExceptionSupplier(giftCertificateEntity.getId(), messageSource));
        UpdateRequestUtils.copyNotNullOrEmptyProperties(giftCertificateEntity, giftCertificateFoundById);
        return create(giftCertificateFoundById);
    }

    /**
     * Method finds gift certificate by id.
     * <p>
     * If gift certificate doesn't exist throw exception.
     * Throws  {@link GiftCertificateNotFoundException} if nothing found
     *
     * @param id requested parameter
     * @return {@link  GiftCertificateEntity} found object
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public GiftCertificateEntity findById(long id) {
        return giftCertificateRepository.findById(id)
                .orElseThrow(getGiftCertificateNotFoundExceptionSupplier(id, messageSource));
    }

    /**
     * Method finds all gift certificates.
     *
     * @param pageable pagination object
     * @return {@link  Page} of objects
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public Page<GiftCertificateEntity> findAll(Pageable pageable) {
        return giftCertificateRepository.findAll(pageable);
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
        if (tags.size() == 1) {
            return giftCertificateRepository.findAllByTagEntities_Id(
                    tagService.findTagIdByName(
                            tags.get(0).getName()), pageable);
        }
        ArrayList<Long> tagsId = new ArrayList<>();
        tags.forEach(tagEntity -> tagsId.add(tagService.findTagIdByName(tagEntity.getName())));
        return giftCertificateRepository.findAllByTags(tagsId, tagsId.size(), pageable);
    }

    /**
     * Method deletes gift certificate
     * <p>
     * Checks if gift certificate exists by id.
     * Throws {@link GiftCertificateNotFoundException} if it doesn't exist.
     *
     * @param id requested parameter
     * @return found entity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public GiftCertificateEntity delete(long id) {
        GiftCertificateEntity giftCertificateEntity = giftCertificateRepository.findById(id)
                .orElseThrow(getGiftCertificateNotFoundExceptionSupplier(id, messageSource));
        giftCertificateRepository.deleteById(id);
        return giftCertificateEntity;
    }
}
