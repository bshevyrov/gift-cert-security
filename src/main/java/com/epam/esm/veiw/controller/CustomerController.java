package com.epam.esm.veiw.controller;

import com.epam.esm.veiw.facade.CustomerFacade;
import com.epam.esm.veiw.facade.OrderFacade;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.model.CustomerModel;
import com.epam.esm.veiw.model.CustomerModelAssembler;
import com.epam.esm.veiw.model.OrderModel;
import com.epam.esm.veiw.model.OrderModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final CustomerFacade customerFacade;
    private final OrderFacade orderFacade;

    private final CustomerModelAssembler customerModelAssembler;
    private final OrderModelAssembler orderModelAssembler;
    private final PagedResourcesAssembler<CustomerDTO> pagedCustomerResourcesAssembler;
    private final PagedResourcesAssembler<OrderDTO> pagedOrderResourcesAssembler;

    @Autowired
    public CustomerController(CustomerFacade customerFacade, OrderFacade orderFacade, CustomerModelAssembler customerModelAssembler, OrderModelAssembler orderModelAssembler, PagedResourcesAssembler<CustomerDTO> pagedCustomerResourcesAssembler, PagedResourcesAssembler<OrderDTO> pagedOrderResourcesAssembler) {
        this.customerFacade = customerFacade;
        this.orderFacade = orderFacade;
        this.customerModelAssembler = customerModelAssembler;
        this.orderModelAssembler = orderModelAssembler;
        this.pagedCustomerResourcesAssembler = pagedCustomerResourcesAssembler;
        this.pagedOrderResourcesAssembler = pagedOrderResourcesAssembler;
    }


    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<CustomerDTO> findById(@PathVariable long id) {
        return new ResponseEntity<>(customerFacade.findById(id), HttpStatus.OK);

    }

    @RequestMapping(value = "",
            method = RequestMethod.GET)
    public ResponseEntity<CollectionModel<CustomerModel>> findAll(@PageableDefault Pageable pageable) {
        Page<CustomerDTO> all = customerFacade.findAll(pageable);
        PagedModel<CustomerModel> pagedModel = pagedCustomerResourcesAssembler.toModel(all, customerModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/orders",
            method = RequestMethod.GET)
    public ResponseEntity<PagedModel<OrderModel>> findAllOrders(@PathVariable long id,
                                                                @PageableDefault Pageable pageable) {
        Page<OrderDTO> page = orderFacade.findAllByCustomerId(id, pageable);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).findAllOrders(id, pageable)).withSelfRel();
        PagedModel<OrderModel> resourcesAssembler = pagedOrderResourcesAssembler.toModel(page, orderModelAssembler, link);
        return new ResponseEntity<>(resourcesAssembler, HttpStatus.OK);
    }

}
