package com.epam.esm.service;

import com.epam.esm.persistence.entity.TagEntity;

public interface TagService extends BaseService<TagEntity> {
    Long findTagIdByName(String tagName);
}
