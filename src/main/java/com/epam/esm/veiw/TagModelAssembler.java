package com.epam.esm.veiw;

import com.epam.esm.veiw.controller.GiftCertificateController;
import com.epam.esm.veiw.controller.TagController;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.TagDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

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
//        GiftCertificateModel model = instantiateModel(entity);

        TagModel model = new TagModel();

        BeanUtils.copyProperties(entity, model);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findById(entity.getId())).withSelfRel());
        return model;
    }

    @Override
    public CollectionModel<TagModel> toCollectionModel(Iterable<? extends TagDTO> entities) {
        CollectionModel<TagModel> tagsModel = super.toCollectionModel(entities);
        tagsModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findAll()).withSelfRel());
        return tagsModel;
    }

    private List<GiftCertificateModel> toGiftCertificateModel(List<GiftCertificateDTO> giftCertificateDTOList) {
        if (giftCertificateDTOList.isEmpty()) {
            return Collections.emptyList();
        }

        return giftCertificateDTOList.stream()
                .map(giftCertificateDTO -> {
                            GiftCertificateModel giftCertificateModel = new GiftCertificateModel();
                            BeanUtils.copyProperties(giftCertificateDTO,giftCertificateDTO);
//                            tagModel.setId(tagModel.getId());
//                            tagModel.setName(tagModel.getName());
                            giftCertificateModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findById(giftCertificateDTO.getId())).withSelfRel());
                            return giftCertificateModel;
                        }


                ).collect(Collectors.toList());
    }
}



