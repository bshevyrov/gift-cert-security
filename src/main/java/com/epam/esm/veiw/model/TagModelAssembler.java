package com.epam.esm.veiw.model;

import com.epam.esm.veiw.controller.TagController;
import com.epam.esm.veiw.dto.TagDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Class transforms  dto to response model.
 */
@Component
public class TagModelAssembler extends RepresentationModelAssemblerSupport<TagDTO, TagModel> {
    public TagModelAssembler() {
        super(TagController.class, TagModel.class);
    }

    @Override
    public TagModel toModel(TagDTO entity) {
        TagModel model = new TagModel();
        BeanUtils.copyProperties(entity, model);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findById(entity.getId())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findAll(Pageable.unpaged())).withRel("find all"));
        return model;
    }
}



