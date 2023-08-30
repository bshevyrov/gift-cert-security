package com.epam.esm.service.impl;

import com.epam.esm.persistence.entity.TagEntity;
import com.epam.esm.persistence.repository.TagRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TagServiceImplTest {
    @Mock
    private TagRepository tagDAO;

    @InjectMocks
    private TagServiceImpl tagService;


    @BeforeAll
    public void initTagTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        TagEntity tagEntity = new TagEntity();
        tagService.create(tagEntity);
        Mockito.verify(tagDAO, Mockito.times(1)).save(tagEntity);
    }

    @Test
    void findById() {
        long id = 1L;
        tagService.findById(id);
        Mockito.verify(tagDAO, Mockito.times(1)).findById(id);
    }

    @Test
    void findAll() {
        tagService.findAll(Pageable.unpaged());
        Mockito.verify(tagDAO, Mockito.times(1)).findAll(Pageable.unpaged());
    }

    @Test
    void update() {
        assertThrowsExactly(UnsupportedOperationException.class, () -> tagService.update(any()));
    }

    @Test
    void delete() {
        long id = 1L;
        tagService.delete(id);
        Mockito.verify(tagDAO, Mockito.times(1)).deleteById(id);
    }

    @Test
    void findTagIdByName() {
        String tagName = "name";
        tagService.findTagIdByName(tagName);
        Mockito.verify(tagDAO, Mockito.times(1)).findByName(tagName);
    }
}