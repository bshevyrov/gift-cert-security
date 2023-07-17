package com.epam.esm.veiw.controller;

import com.epam.esm.facade.CustomerFacade;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.model.CustomerModel;
import com.epam.esm.veiw.model.CustomerModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final CustomerFacade customerFacade;

    private final CustomerModelAssembler customerModelAssembler;
    private final PagedResourcesAssembler<CustomerDTO> pagedResourcesAssembler;
    @Autowired
    public CustomerController(CustomerFacade customerFacade, CustomerModelAssembler customerModelAssembler, PagedResourcesAssembler<CustomerDTO> pagedResourcesAssembler) {
        this.customerFacade = customerFacade;
        this.customerModelAssembler = customerModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<CustomerDTO> findById(@PathVariable long id) {
        return new ResponseEntity<>(customerFacade.findById(id), HttpStatus.OK);

    }

    @RequestMapping(value = "",
            method = RequestMethod.GET)
    public ResponseEntity<CollectionModel<CustomerModel>> findAll(@RequestParam Pageable pageable) {
        Page<CustomerDTO> all = customerFacade.findAll(pageable);
        PagedModel<CustomerModel> pagedModel = pagedResourcesAssembler.toModel(all, customerModelAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }


}
