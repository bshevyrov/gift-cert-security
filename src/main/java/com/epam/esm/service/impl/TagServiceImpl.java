package com.epam.esm.service.impl;

import com.epam.esm.exception.tag.TagExistException;
import com.epam.esm.exception.tag.TagNotFoundException;
import com.epam.esm.persistence.dao.TagDAO;
import com.epam.esm.persistence.entity.TagEntity;
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
     * <p>
     * Checks if tag with this name exist
     * - if true throws exception
     *
     * @param tagEntity object for creation.
     * @return saved entity.
     * @throws {@link TagNotFoundException}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public TagEntity create(TagEntity tagEntity) {

        if (tagDAO.existsByName(tagEntity.getName())) {
            throw new TagExistException(messageSource.getMessage("tag.exist.exception",
                    new Object[]{tagEntity.getName()},
                    LocaleContextHolder.getLocale()));
        }
        return tagDAO.create(tagEntity);
    }

    /**
     * Method return tag that was found by id.
     * <p>
     * Checks if tag with this name exists.
     * - if false throws exception.
     *
     * @param id tag id values
     * @return tag entity
     * @throws {@link TagNotFoundException}
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public TagEntity findById(long id) {
        return tagDAO.findById(TagEntity.class, id)
                .orElseThrow(() -> new TagNotFoundException(messageSource.getMessage("tag.notfound.exception",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

    /**
     * Method finds all tags with pagination.
     *
     * @param pageable pagination object
     * @return {@link Page} of tags
     */
    @Override
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public Page<TagEntity> findAll(Pageable pageable) {
        return tagDAO.findAll(TagEntity.class, pageable);
    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    @Deprecated
    public TagEntity update(TagEntity tagEntity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method deletes tag.
     * If there is not TagEntity with corresponding id throws exception.
     *
     * @param id request parameter.
     * @return deleted {@link TagEntity}
     * @throws TagNotFoundException
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public TagEntity delete(long id) {
        return tagDAO.deleteById(TagEntity.class, id).orElseThrow(() ->
                new TagNotFoundException(messageSource.getMessage("tag.notfound.exception",
                        new Object[]{id},
                        LocaleContextHolder.getLocale())));
    }

    /**
     * Finds tag id by tag na
     *
     * @param tagName String request parameter.
     * @return Long id
     * @throws TagNotFoundException
     */
    @Transactional(rollbackFor = {Exception.class}, readOnly = true)
    public Long findTagIdByName(String tagName) {
        return tagDAO.findByName(tagName).orElseThrow(() ->
                new TagNotFoundException(messageSource.getMessage("tag.notfound.exception",
                        new Object[]{tagName},
                        LocaleContextHolder.getLocale()))).getId();

    }
}
