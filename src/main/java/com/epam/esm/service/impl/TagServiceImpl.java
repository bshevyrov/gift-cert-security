package com.epam.esm.service.impl;

import com.epam.esm.persistence.dao.TagDAO;
import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.exception.tag.TagExistException;

import com.epam.esm.exception.tag.TagNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Used  to manipulate GiftCertificate objects and collecting data.
 */

@Service
@Transactional(rollbackFor = {Exception.class},readOnly = true)

public class TagServiceImpl implements TagService {
    private final MessageSource messageSource;
    private final TagDAO tagDAO;

    @Autowired

    public TagServiceImpl(MessageSource messageSource, TagDAO tagDAO) {
        this.messageSource = messageSource;
        this.tagDAO = tagDAO;
    }


    /**
     * Method creates tag.

     * Checks if tag with this name exist
     * - if true throws TagExistException
     *
     * @param tagEntity object for creation
     * @return id fon created object
     */
    @Override
    public TagEntity create(TagEntity tagEntity) {

        if (tagDAO.existsByName(tagEntity.getName())) {
            throw new TagExistException(messageSource.getMessage("tag.exist.exception",
                    new Object[]{tagEntity.getName()},
                    LocaleContextHolder.getLocale()));
        }
        return tagDAO.create(tagEntity);
    }

    /**
     * Method return tag that was found by id

     * Checks if tag with this name exist
     * - if false throws TagNotFoundException
     *
     * @param id tag id values
     * @return tag entity
     */
    @Override
    public TagEntity findById(long id) {

        return tagDAO.findById(TagEntity.class,id)
                .orElseThrow(() -> new TagNotFoundException(messageSource.getMessage("tag.notfound.exceptoion",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

    /**
     * Method finds all tags
     *
     * @return List of tags
     */
    @Override
    public Page<TagEntity> findAll(Pageable pageable) {
        return  tagDAO.findAll(TagEntity.class,pageable);
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public void update(TagEntity tagEntity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method deletes tag.
     * Checks if id valid
     * - if false throws TagIdException
     * Then checks if tag exist by id
     * - if false throws TagNotFoundException
     *
     * @param id request parameter
     */
    @Override
    public TagEntity delete(long id) {
//
//        if (!tagDAO.existsById(id)) {
//            throw new TagNotFoundException(messageSource.getMessage("tag.notfound.exceptoion",
//                    new Object[]{id},
//                    LocaleContextHolder.getLocale()));
//        }
        TagEntity tagEntity = tagDAO.deleteById(TagEntity.class, id).orElseThrow(() ->
                new TagNotFoundException(messageSource.getMessage("tag.notfound.exceptoion",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
        return tagEntity;
    }

    /**
     * Method finds all tags by connected giftCertificate.
     * Checks if id valid
     * * - if false throws GiftCertificateIdException exception
     * * Then checks if giftCertificate exist by id
     * * - if false throws GiftCertificateNotFoundException
     *
     * @param id giftCertificate id
     * @return List of tags
     */

/*    @Override
    public List<Tag> findAllByGiftCertificateId(long id) {
        if (!InputVerification.verifyId(id)) {
            throw new GiftCertificateIdException(id);
        }
        if (!tagRepository.existsById(id)) {
            throw new GiftCertificateNotFoundException(id);
        }
        return tagRepository.findAllByGiftCertificates_id(id);
    }*/
}
