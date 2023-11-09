package com.epam.esm.veiw.facade.impl;

import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.TagDTO;
import com.epam.esm.veiw.facade.GiftCertificateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class used for conversion {@link GiftCertificateEntity}  and {@link GiftCertificateDTO}.
 */
@Component
public class GiftCertificateFacadeImpl implements GiftCertificateFacade {

    private final GiftCertificateMapper giftCertificateMapper;
    private final TagMapper tagMapper;
    private final GiftCertificateService giftCertificateService;


    @Autowired
    public GiftCertificateFacadeImpl(GiftCertificateMapper giftCertificateMapper, TagMapper tagMapper, GiftCertificateService giftCertificateService) {
        this.giftCertificateMapper = giftCertificateMapper;
        this.tagMapper = tagMapper;
        this.giftCertificateService = giftCertificateService;
    }

    /**
     * Method consumes giftCertificateDTO and return  created giftCertificate.
     *
     * @param giftCertificateDTO dto object
     * @return {@link GiftCertificateDTO}
     */
    @Override
    public GiftCertificateDTO create(GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateMapper.toDTO(
                giftCertificateService.create(
                        giftCertificateMapper.toEntity(giftCertificateDTO)));
    }

    /**
     * Method consumes id value and return dto object.
     *
     * @param id request parameter
     * @return {@link  GiftCertificateDTO} found object
     */
    @Override
    public GiftCertificateDTO findById(long id) {
        return giftCertificateMapper.toDTO(giftCertificateService.findById(id));
    }


    /**
     * Method consumes pagination object and string search query.
     * Produce list of dto object.
     *
     * @param pageable pagination objects
     * @param query string objects
     * @return {@page} of dtos
     */
    @Override
    public Page<GiftCertificateDTO> findAllByNameLikeOrDescriptionLike(String query, Pageable pageable) {
        String searchRequest = query.substring(
                query.indexOf(":")+1)
                .replace("\"","")
                .replace("}","")
                .trim();
        Page<GiftCertificateEntity> entities =
                giftCertificateService.findAllByNameLikeOrDescriptionLike(searchRequest, pageable);
        return new PageImpl<>(giftCertificateMapper.toDTOList(entities.getContent()), pageable, entities.getTotalElements());    }

    /**
     * Method consumes pagination object.
     * Produce list of dto object.
     *
     * @param pageable pagination objects
     * @return {@page} of dtos
     */
    @Override
    public Page<GiftCertificateDTO> findAll(Pageable pageable) {
        Page<GiftCertificateEntity> entities =
                giftCertificateService.findAll(pageable);
        return new PageImpl<>(giftCertificateMapper.toDTOList(entities.getContent()), pageable, entities.getTotalElements());
    }

    /**
     * Finds  GiftCertificate by corresponding tags.
     *
     * @param tags     {@link List} of {@link TagDTO}
     * @param pageable pagination object.
     * @return {@link Page} of found {@link GiftCertificateDTO}
     */
    @Override
    public Page<GiftCertificateDTO> findAllByTagsName(List<TagDTO> tags, Pageable pageable) {
        Page<GiftCertificateEntity> giftCertificateEntities = giftCertificateService.findAllByTagsName(tagMapper.toEntityList(tags), pageable);
        return new PageImpl<>(giftCertificateMapper.toDTOList(giftCertificateEntities.getContent()), pageable, giftCertificateEntities.getTotalElements());
    }

    /**
     * Consumes dto update gift certificate.
     *
     * @param giftCertificateDTO dto object
     */
    @Override
    public GiftCertificateDTO update(GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateMapper.toDTO(
                giftCertificateService.update(
                        giftCertificateMapper.toEntity(giftCertificateDTO)));
    }

    /**
     * Consumes id value and  deletes gift certificate
     *
     * @param id requested parameter
     */
    @Override
    public void delete(long id) {
        giftCertificateService.delete(id);
    }
}
