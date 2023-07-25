package com.epam.esm.service.impl;

import com.epam.esm.entity.TagEntity;
import com.epam.esm.exception.tag.TagExistException;
import com.epam.esm.exception.tag.TagIdException;
import com.epam.esm.exception.tag.TagNameException;
import com.epam.esm.exception.tag.TagNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.util.InputVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Used  to manipulate GiftCertificate objects and collecting data.
 */

@Service
public class TagServiceImpl implements TagService {
    private  final TagRepository tagRepository;
    @Autowired

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    /**
     * Method creates tag.
     * Checks if tag have valid name
     * - if false throws{@link  TagNameException}
     * Checks if tag with this name exist
     * - if true throws TagExistException
     *
     * @param tagEntity object for creation
     * @return id fon created object
     */
    @Override
    public TagEntity create(TagEntity tagEntity) {
        if (!InputVerification.verifyName(tagEntity.getName())) {
            throw new TagNameException(tagEntity.getName());
        }
        if (!tagRepository.existsByName(tagEntity.getName())) {
            throw new TagExistException(tagEntity.getName());
        }
        return tagRepository.save(tagEntity);
    }

    /**
     * Method return tag that was found by id
     * Checks if tag have valid id
     * - if false throws{@link  TagIdException}
     * Checks if tag with this name exist
     * - if false throws TagNotFoundException
     *
     * @param id tag id values
     * @return tag entity
     */
    @Override
    public TagEntity findById(long id) {
        if (!InputVerification.verifyId(id)) {
            throw new TagIdException(id);
        }
        return tagRepository.findById(id).orElseThrow(() -> new TagNotFoundException(id));
    }

    /**
     * Method finds all tags
     *
     * @return List of tags
     */
    @Override
    public List<TagEntity> findAll() {
        return (List<TagEntity>) tagRepository.findAll();
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
    public void delete(long id) {
        if (!InputVerification.verifyId(id)) {
            throw new TagIdException(id);
        }
        if (!tagRepository.existsById(id)) {
            throw new TagNotFoundException(id);
        }
        tagRepository.deleteById(id);
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
