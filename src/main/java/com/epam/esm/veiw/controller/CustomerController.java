package com.epam.esm.veiw.controller;

import com.epam.esm.util.validation.group.Purchase;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.dto.OrderDTO;
import com.epam.esm.veiw.dto.OrderItemDTO;
import com.epam.esm.veiw.facade.CustomerFacade;
import com.epam.esm.veiw.facade.OrderFacade;
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
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/v1/customers",
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

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CustomerModel>> findAll(@PageableDefault Pageable pageable) {
        Page<CustomerDTO> all = customerFacade.findAll(pageable);
        PagedModel<CustomerModel> pagedModel = pagedCustomerResourcesAssembler.toModel(all, customerModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/orders")
    public ResponseEntity<PagedModel<OrderModel>> findAllOrders(@PathVariable long id,
                                                                @PageableDefault Pageable pageable) {
        Page<OrderDTO> all = orderFacade.findAllByCustomerId(id, pageable);
        PagedModel<OrderModel> pagedModel = pagedOrderResourcesAssembler.toModel(all, orderModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(value = "{id}/orders")
    public ResponseEntity<OrderModel> createOrderByOrderItems(@Validated(Purchase.class) @RequestBody List<OrderItemDTO> orderItemDTOS,
                                                              @PathVariable @Positive long id,
                                                              UriComponentsBuilder ucb) {

        OrderDTO orderDTO = new OrderDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        orderDTO.setCustomerDTO(customerDTO);

        orderItemDTOS.forEach(
                orderItemDTO -> orderItemDTO.setOrderDTO(orderDTO));
        orderDTO.setOrderItemDTOS(orderItemDTOS);
        orderItemDTOS.forEach(orderItemDTO -> orderItemDTO.setOrderDTO(orderDTO));
        OrderDTO dto = orderFacade.create(orderDTO);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/customer/" + id + "/orders/").path(String.valueOf(dto.getId()))
                .build().toUri();

        headers.setLocation(locationUri);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @GetMapping(value = "{id}/orders/popularity")
    public ResponseEntity<OrderModel> getPopularTagInOrderByCustomerId(@Positive @PathVariable long id) {
        OrderDTO popularTagInOrderByCustomerId = orderFacade.getPopularTagInOrderByCustomerId(id);
        OrderModel orderModel = orderModelAssembler.toModel(popularTagInOrderByCustomerId);
        return new ResponseEntity<>(orderModel, HttpStatus.OK);
    }
}
