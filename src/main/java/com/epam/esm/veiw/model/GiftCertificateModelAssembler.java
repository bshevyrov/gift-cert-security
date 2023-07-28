package com.epam.esm.veiw.model;

import com.epam.esm.veiw.controller.GiftCertificateController;
import com.epam.esm.veiw.controller.TagController;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.TagDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GiftCertificateModelAssembler extends RepresentationModelAssemblerSupport<GiftCertificateDTO, GiftCertificateModel> {


    public GiftCertificateModelAssembler() {
        super(GiftCertificateController.class, GiftCertificateModel.class);
    }


    @Override
    public GiftCertificateModel toModel(GiftCertificateDTO entity) {
//        GiftCertificateModel model = instantiateModel(entity);

        GiftCertificateModel model = new GiftCertificateModel();

        BeanUtils.copyProperties(entity, model);
        model.setTagModelList(toTagModel(entity.getTagDTOS()));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findById(entity.getId())).withSelfRel());
        return model;
    }

//    @Override
//    public CollectionModel<GiftCertificateModel> toCollectionModel(Iterable<? extends GiftCertificateDTO> entities) {
//        CollectionModel<GiftCertificateModel> tagsModel = super.toCollectionModel(entities);
//        tagsModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findAll()).withSelfRel());
//        return tagsModel;
//    }

    private List<TagModel> toTagModel(List<TagDTO> tags) {
        if (tags.isEmpty()) {
            return Collections.emptyList();
        }

        return tags.stream()
                .map(tagDTO -> {
                            TagModel tagModel = new TagModel();
                            BeanUtils.copyProperties(tagDTO,tagModel);
//                            tagModel.setId(tagModel.getId());
//                            tagModel.setName(tagModel.getName());
                            tagModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findById(tagDTO.getId())).withSelfRel());
                            return tagModel;
                        }


                ).collect(Collectors.toList());
    }
}



