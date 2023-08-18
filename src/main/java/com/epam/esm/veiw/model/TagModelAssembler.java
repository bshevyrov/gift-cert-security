package com.epam.esm.veiw.model;

import com.epam.esm.veiw.controller.GiftCertificateController;
import com.epam.esm.veiw.controller.TagController;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.TagDTO;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).deleteById(entity.getId())).withRel("delete"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findAll(Pageable.unpaged())).withRel("find all"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).create(new TagDTO(),UriComponentsBuilder.newInstance())).withRel("create"));

        return model;
    }

}



