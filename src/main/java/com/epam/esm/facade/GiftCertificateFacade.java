package com.epam.esm.facade;

import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GiftCertificateFacade extends BaseFacade<GiftCertificateDTO> {

    Page<GiftCertificateDTO> findAll(Pageable pageable);
}