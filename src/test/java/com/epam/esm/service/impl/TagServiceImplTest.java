package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.persistence.repository.TagRepository;
import com.epam.esm.service.TagService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService ;


    @Test
    void create() {
        Assertions.assertTrue(true);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
        Assertions.assertThrowsExactly(UnsupportedOperationException.class,
                ()->tagService.update(new TagEntity()));
    }

    @Test
    void delete() {
    }

    @Test
    void findTagIdByName() {
    }
}