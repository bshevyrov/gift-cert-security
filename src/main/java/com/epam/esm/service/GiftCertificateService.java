package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.veiw.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GiftCertificateService extends BaseService<GiftCertificate> {

    Page<GiftCertificate> findAll(Pageable pageable);
}
