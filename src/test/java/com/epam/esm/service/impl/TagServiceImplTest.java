package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.TagEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    private TagServiceImpl tagService;

    @Test
    void create() {
        assertDoesNotThrow(() -> tagService.create(new TagEntity()));
    }

    @Test
    void findById() {
        assertDoesNotThrow(() -> tagService.findById(anyLong()));
    }

    @Test
    void findAll() {
        assertDoesNotThrow(() -> tagService.findAll(Pageable.unpaged()));
    }

    @Test
    void update() {
        assertDoesNotThrow(
                () -> tagService.update(new TagEntity()));
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> tagService.delete(anyLong()));
    }

    @Test
    void findTagIdByName() {
        assertDoesNotThrow(() -> tagService.findTagIdByName(anyString()));
    }
}