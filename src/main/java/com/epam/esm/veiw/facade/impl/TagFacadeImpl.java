package com.epam.esm.veiw.facade.impl;

import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.veiw.facade.TagFacade;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.veiw.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class used for conversion  tag and tagDTO.
 */
@Component
public class TagFacadeImpl implements TagFacade {
    private final TagMapper tagMapper;
    private final TagService tagService;

    @Autowired
    public TagFacadeImpl(TagMapper tagMapper, TagService tagService) {
        this.tagMapper = tagMapper;
        this.tagService = tagService;
    }

    /**
     * Method consume tagDTO and return id of created giftCertificate.
     *
     * @param tagDTO dto object
     * @return id
     */
    @Override
    public TagDTO create(TagDTO tagDTO) {
        return tagMapper.toDTO(
                tagService.create(
                        tagMapper.toEntity(tagDTO)));
    }

    /**
     * Method consume id value and return dto object.
     *
     * @param id request parameter
     * @return {@link  TagDTO} created object
     */
    @Override
    public TagDTO findById(long id) {
        return tagMapper.toDTO(tagService.findById(id));
    }

    /**
     * Method produce list of dto object.
     *
     * @return List of dtos
     */
    @Override
    public Page<TagDTO> findAll(Pageable pageable) {
        Page<TagEntity> all = tagService.findAll(pageable);
        return new PageImpl<>( tagMapper.toDTOList(all.getContent()),pageable,all.getTotalElements());
    }

    /**
     * Consumes dto update tag.
     *
     * @param tagDTO dto object
     */
    @Override
    public void update(TagDTO tagDTO) {
        tagService.update(tagMapper.toEntity(tagDTO));
    }

    /**
     * Consumes id value and deletes tag.
     *
     * @param id requested parameter
     */
    @Override
    public void delete(long id) {
        tagService.delete(id);
    }
}
