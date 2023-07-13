package com.epam.esm.facade.impl;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.facade.GiftCertificateFacade;
import com.epam.esm.mapper.GiftCertificateListMapper;
import com.epam.esm.mapper.GiftCertificateMapper;
import com.epam.esm.mapper.TagListMapper;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class used for conversion giftCertificate  and giftCertificateFacadeImplDTO.
 */
@Component
public class GiftCertificateFacadeImpl implements GiftCertificateFacade {

    private final GiftCertificateMapper giftCertificateMapper;
    private final GiftCertificateListMapper giftCertificateListMapper;
    private final GiftCertificateService giftCertificateService;
    private final TagListMapper tagListMapper;
    private final TagService tagService;


    @Autowired
    public GiftCertificateFacadeImpl(GiftCertificateMapper giftCertificateMapper, GiftCertificateListMapper giftCertificateListMapper, GiftCertificateService giftCertificateService, TagListMapper tagListMapper, TagService tagService) {
        this.giftCertificateMapper = giftCertificateMapper;
        this.giftCertificateListMapper = giftCertificateListMapper;
        this.giftCertificateService = giftCertificateService;
        this.tagListMapper = tagListMapper;
        this.tagService = tagService;
    }

    /**
     * Method consume giftCertificateDTO and return id of created giftCertificate.
     *
     * @param giftCertificateDTO dto object
     * @return id
     */
    @Override
    public GiftCertificateDTO create(GiftCertificateDTO giftCertificateDTO) {
        return giftCertificateMapper.toDTO(
                giftCertificateService.create(
                        giftCertificateMapper.toModel(giftCertificateDTO)));
    }

    /**
     * Method consume id value and return dto object.
     *
     * @param id request parameter
     * @return {@link  GiftCertificateDTO} created object
     */
    @Override
    public GiftCertificateDTO findById(long id) {

        return giftCertificateMapper.toDTO(giftCertificateService.findById(id));

    }

    /**
     * Guaranteed to throw an exception and leave.
     *
     * @throws UnsupportedOperationException always
     * @deprecated Unsupported operation.
     */
    @Override
    public List<GiftCertificateDTO> findAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method consume sort searchRequest.
     * Produce list of dto object.
     *
     * @param pageable sort and search parameters
     * @return list of dtos
     */
    @Override
    public Page<GiftCertificateDTO> findAll(Pageable pageable) {

        Page<GiftCertificate> entities =
                giftCertificateService.findAll(pageable);
        return new PageImpl<>(giftCertificateListMapper.toDTOList(entities.getContent()), pageable, entities.getTotalElements());
    }

    /**
     * Consumes dto update gift certificate.
     *
     * @param giftCertificateDTO dto object
     */
    @Override
    public void update(GiftCertificateDTO giftCertificateDTO) {
        giftCertificateService.update(giftCertificateMapper.toModel(giftCertificateDTO));
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
