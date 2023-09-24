package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.GiftCertificateEntity;
import com.epam.esm.persistence.entity.TagEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import sun.jvm.hotspot.debugger.Page;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
@ExtendWith(MockitoExtension.class)

class GiftCertificateServiceImplTest {
    @Mock
    private GiftCertificateServiceImpl giftCertificateService;

    @Test
    void create() {
        assertDoesNotThrow(
                () -> giftCertificateService.create(new GiftCertificateEntity()));
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> giftCertificateService.update(new GiftCertificateEntity()));
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> giftCertificateService.findById(
                anyLong()));
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> giftCertificateService.findAll(Pageable.unpaged()));
    }

    @Test
    void findAllByTagsName() {
        assertDoesNotThrow(() -> giftCertificateService.findAllByTagsName(new ArrayList<>(),Pageable.unpaged()));
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> giftCertificateService.delete(anyLong()));

    }
}