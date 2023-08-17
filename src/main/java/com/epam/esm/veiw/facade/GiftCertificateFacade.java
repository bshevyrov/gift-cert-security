package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.TagDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface GiftCertificateFacade extends BaseFacade<GiftCertificateDTO> {

    Page<GiftCertificateDTO> findAll(Pageable pageable);

    Page<GiftCertificateDTO> findAllByTagsName(List<TagDTO> tags, Pageable pageable);
}
