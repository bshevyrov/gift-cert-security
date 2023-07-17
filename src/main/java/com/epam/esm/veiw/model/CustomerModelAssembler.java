package com.epam.esm.veiw.model;

import com.epam.esm.veiw.controller.CustomerController;
import com.epam.esm.veiw.dto.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<CustomerDTO, CustomerModel> {


    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerModel.class);
    }


    @Override
    public CustomerModel toModel(CustomerDTO entity) {
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(entity, model);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).findById(entity.getId())).withSelfRel());
        return model;
    }
}



