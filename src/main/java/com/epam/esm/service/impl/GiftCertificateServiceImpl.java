package com.epam.esm.service.impl;

import com.epam.esm.entity.GiftCertificateEntity;
import com.epam.esm.exception.giftcertificate.GiftCertificateIdException;
import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.exception.tag.TagNameException;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.util.InputVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Used  to manipulate GiftCertificate objects and collecting data.
 */
@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private final GiftCertificateRepository giftCertificateRepository;

    @Autowired

    public GiftCertificateServiceImpl(GiftCertificateRepository giftCertificateRepository) {
        this.giftCertificateRepository = giftCertificateRepository;
    }


    /**
     * Method creates gift certificate.
     * Checks if tag have valid name
     * - if false throws{@link  TagNameException}
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
       /* long giftCertificateId = giftCertificateDAO.create(giftCertificate);
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
        giftCertificateEntity.getTagEntities().forEach(tag -> {
            if (!InputVerification.verifyName(tag.getName())) {
                throw new TagNameException(tag.getName());
            }
        });
        return giftCertificateRepository.save(giftCertificateEntity);
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
    public GiftCertificateEntity findById(long id) {
        if (!InputVerification.verifyId(id)) {
            throw new GiftCertificateIdException(id);
        }
        return giftCertificateRepository.findById(id).orElseThrow(() ->
                new GiftCertificateNotFoundException(id));
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
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public List<GiftCertificateEntity> findAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method finds all gift certificates based on search param, add tags for each one, and sort items.
     *
     * @param pageable entity
     * @return List of objects
     */
    @Override
    public Page<GiftCertificateEntity> findAll(Pageable pageable) {
        /*List<GiftCertificate> giftCertificateList = giftCertificateRepository.findAll(searchRequest);
        giftCertificateList.forEach(giftCertificate -> giftCertificate.setTags((Set<Tag>) tagDAO.findAllByGiftCertificateId(giftCertificate.getId())));
        return giftCertificateList;*/
//        Pageable pageable = PageRequest.of(0,2);
        return giftCertificateRepository.findAll(pageable);
    }

    /**
     * Method updates gift certificate.
     * Checks if giftCertificate  if id valid:
     * - if true proceed to next operation
     * * - if false thrown {@link GiftCertificateIdException}
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
        if (!InputVerification.verifyId(giftCertificateEntity.getId())) {
            throw new GiftCertificateIdException(giftCertificateEntity.getId());
        }
        if (!giftCertificateRepository.existsById(giftCertificateEntity.getId())) {
            throw new GiftCertificateNotFoundException(giftCertificateEntity.getId());
        }
        giftCertificateRepository.save(giftCertificateEntity);
    }

    /**
     * Method deletes gift certificate
     * Checks if gift certificate id is valid:
     * - if false throws {@link  GiftCertificateIdException}
     * Checks if gift certificate exists by id:
     * - if true - deletes from database
     * - if false - throws GiftCertificateNotFoundException exception
     *
     * @param id requested parameter
     */
    @Override
    public void delete(long id) {
        if (!InputVerification.verifyId(id)) {
            throw new GiftCertificateIdException(id);
        }
        if (!giftCertificateRepository.existsById(id)) {
            throw new GiftCertificateNotFoundException(id);
        }
        giftCertificateRepository.deleteById(id);
    }
}
