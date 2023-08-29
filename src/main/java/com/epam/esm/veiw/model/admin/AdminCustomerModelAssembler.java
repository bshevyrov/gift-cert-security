package com.epam.esm.veiw.model.admin;

import com.epam.esm.veiw.controller.CustomerController;
import com.epam.esm.veiw.controller.admin.AdminCustomerController;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.model.CustomerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Class transforms  dto to response model.
 */
@Component
public class AdminCustomerModelAssembler extends RepresentationModelAssemblerSupport<CustomerDTO, CustomerModel> {

    public AdminCustomerModelAssembler() {
        super(AdminCustomerController.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(CustomerDTO customerDTO) {
        CustomerModel model = new CustomerModel();
        BeanUtils.copyProperties(customerDTO, model);
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AdminCustomerController.class).findById(customerDTO.getId())).withSelfRel());
        return model;
    }
}



