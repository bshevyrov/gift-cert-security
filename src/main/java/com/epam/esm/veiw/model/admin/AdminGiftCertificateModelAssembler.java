package com.epam.esm.veiw.model.admin;

import com.epam.esm.veiw.controller.TagController;
import com.epam.esm.veiw.controller.admin.AdminGiftCertificateController;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.TagDTO;
import com.epam.esm.veiw.model.GiftCertificateModel;
import com.epam.esm.veiw.model.TagModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class transforms  dto to response model.
 */
@Component
public class AdminGiftCertificateModelAssembler extends RepresentationModelAssemblerSupport<GiftCertificateDTO, GiftCertificateModel> {
    public AdminGiftCertificateModelAssembler() {
        super(AdminGiftCertificateController.class, GiftCertificateModel.class);
    }

    @Override
    public GiftCertificateModel toModel(GiftCertificateDTO entity) {
        GiftCertificateModel model = new GiftCertificateModel();
        BeanUtils.copyProperties(entity, model);
        model.setTagModels(toTagModel(entity.getTagDTOS()));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminGiftCertificateController.class).findById(entity.getId())).withSelfRel());
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminGiftCertificateController.class).findAll(Pageable.unpaged())).withRel("find all"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminGiftCertificateController.class).findAllByTagsName(Pageable.unpaged(), new ArrayList<>())).withRel("find gift cert by tag name"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminGiftCertificateController.class).deleteById(entity.getId())).withRel("delete"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminGiftCertificateController.class).update(new GiftCertificateDTO(), entity.getId())).withRel("update"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminGiftCertificateController.class).create(new GiftCertificateDTO(), UriComponentsBuilder.newInstance())).withRel("create"));
        return model;
    }

    private List<TagModel> toTagModel(List<TagDTO> tags) {
        if (tags.isEmpty()) {
            return Collections.emptyList();
        }
        return tags.stream()
                .map(tagDTO -> {
                            TagModel tagModel = new TagModel();
                            BeanUtils.copyProperties(tagDTO, tagModel);
                            tagModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findById(tagDTO.getId())).withSelfRel());
                            return tagModel;
                        }
                ).collect(Collectors.toList());
    }
}



