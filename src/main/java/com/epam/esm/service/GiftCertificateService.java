package com.epam.esm.service;

import com.epam.esm.exception.giftcertificate.GiftCertificateNotFoundException;
import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.TagEntity;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Supplier;

public interface GiftCertificateService extends BaseService<GiftCertificateEntity> {
    Page<GiftCertificateEntity> findAllByTagsName(List<TagEntity> tags, Pageable pageable);

    /**
     * Creates exception.
     *
     * @param id value parameter
     * @return {@link GiftCertificateNotFoundException}
     */
    default Supplier<GiftCertificateNotFoundException> getGiftCertificateNotFoundExceptionSupplier(Long id, MessageSource messageSource) {
        return () -> new GiftCertificateNotFoundException(messageSource.getMessage("gift.certificate.notfound.exception",
                new Object[]{"id - " + id},
                LocaleContextHolder.getLocale()));
    }
}
