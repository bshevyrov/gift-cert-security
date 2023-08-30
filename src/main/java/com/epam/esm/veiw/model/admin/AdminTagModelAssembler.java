package com.epam.esm.veiw.model.admin;

import com.epam.esm.veiw.controller.admin.AdminTagController;
import com.epam.esm.veiw.dto.TagDTO;
import com.epam.esm.veiw.model.TagModel;
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
public class AdminTagModelAssembler extends RepresentationModelAssemblerSupport<TagDTO, TagModel> {
    public AdminTagModelAssembler() {
        super(AdminTagController.class, TagModel.class);
    }

    @Override
    public TagModel toModel(TagDTO entity) {
        TagModel model = new TagModel();
        BeanUtils.copyProperties(entity, model);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminTagController.class).findById(entity.getId())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminTagController.class).deleteById(entity.getId())).withRel("delete"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminTagController.class).findAll(Pageable.unpaged())).withRel("find all"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminTagController.class).create(new TagDTO(), UriComponentsBuilder.newInstance())).withRel("create"));
        return model;
    }
}



