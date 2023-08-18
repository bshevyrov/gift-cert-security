package com.epam.esm.veiw.model;

import com.epam.esm.veiw.controller.CustomerController;
import com.epam.esm.veiw.dto.OrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler extends RepresentationModelAssemblerSupport<OrderDTO, OrderModel> {

    @Autowired
    public OrderModelAssembler() {
        super(CustomerController.class, OrderModel.class);
    }


    @Override
    public OrderModel toModel(OrderDTO entity) {
        OrderModel model = new OrderModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

}



