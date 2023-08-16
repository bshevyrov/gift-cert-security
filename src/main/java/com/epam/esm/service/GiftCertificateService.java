package com.epam.esm.service;

import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GiftCertificateService extends BaseService<GiftCertificateEntity> {


    Page<GiftCertificateEntity> findAllByTagsName(List<TagEntity> tags,Pageable pageable);
}
