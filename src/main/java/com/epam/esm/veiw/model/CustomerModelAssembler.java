package com.epam.esm.veiw.model;

import com.epam.esm.veiw.controller.CustomerController;
import com.epam.esm.veiw.controller.GiftCertificateController;
import com.epam.esm.veiw.controller.TagController;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.dto.TagDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<CustomerDTO, CustomerModel> {


    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerModel.class);
    }


    @Override
    public CustomerModel toModel(CustomerDTO entity) {
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(entity, model);
        model.setOrderModels(toOrderModel(entity.getOrderDTOS()));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).findById(entity.getId())).withSelfRel());
        return model;
    }

    private List<OrderModel> toOrderModel(List<OrderDTO> orderDTOS) {
        if (orderDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        return orderDTOS.stream()
                .map(orderDTO -> {
                            OrderModel orderModel = new OrderModel();
                            BeanUtils.copyProperties(orderDTO,orderModel);
                            orderModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).findById(orderDTO.getId())).withSelfRel());
                            return orderModel;
                        }
                ).collect(Collectors.toList());
    }
}



