package com.epam.esm.veiw.model;

import com.epam.esm.veiw.controller.CustomerController;
import com.epam.esm.veiw.dto.GiftCertificateDTO;
import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.dto.OrderItemDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<OrderDTO, OrderModel> {
private final GiftCertificateModelAssembler giftCertificateModelAssembler;
@Autowired
    public OrderModelAssembler(GiftCertificateModelAssembler giftCertificateModelAssembler) {
        super(CustomerController.class, OrderModel.class);
        this.giftCertificateModelAssembler = giftCertificateModelAssembler;
    }


    @Override
    public OrderModel toModel(OrderDTO entity) {
//        GiftCertificateModel model = instantiateModel(entity);

        OrderModel model = new OrderModel();

        BeanUtils.copyProperties(entity, model);
        model.setOrderItemModelList(toOrderItemModel(entity.getOrderItemDTOS()));
//        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).findAllOrders(entity.getCustomerDTO().getId())).withSelfRel());
        return model;
    }
//        public OrderModel toModel(OrderDTO entity, Pageable pageable) {
////        GiftCertificateModel model = instantiateModel(entity);
//
//        OrderModel model = new OrderModel();
//
//        BeanUtils.copyProperties(entity, model);
////        model.setOrderItemModels(toTagModel(entity.getOrderItemDTOS()));
//        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).findAllOrders(entity.getCustomerDTO().getId(),pageable)).withSelfRel());
//        return model;
//    }

//    @Override
//    public CollectionModel<GiftCertificateModel> toCollectionModel(Iterable<? extends GiftCertificateDTO> entities) {
//        CollectionModel<GiftCertificateModel> tagsModel = super.toCollectionModel(entities);
//        tagsModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GiftCertificateController.class).findAll()).withSelfRel());
//        return tagsModel;
//    }

    private List<OrderItemModel> toOrderItemModel(List<OrderItemDTO> orderItemDTOS) {
        if (orderItemDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        return orderItemDTOS.stream()
                .map(orderItemDTO -> {
                            OrderItemModel orderItemModel = new OrderItemModel();
                            BeanUtils.copyProperties(orderItemDTO,orderItemModel);
                            orderItemModel.setGiftCertificateModel(giftCertificateModelAssembler.toModel(orderItemDTO.getGiftCertificateDTO()));
//                            tagModel.setId(tagModel.getId());
//                            tagModel.setName(tagModel.getName());
//                            orderItemModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findById(tagDTO.getId())).withSelfRel());
                            return orderItemModel;
                        }


                ).collect(Collectors.toList());
    }
}



