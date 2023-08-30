//package com.epam.esm.veiw.controller.admin;
//
//import com.epam.esm.veiw.dto.CustomerDTO;
//import com.epam.esm.veiw.facade.CustomerFacade;
//import com.epam.esm.veiw.model.CustomerModel;
//import com.epam.esm.veiw.model.CustomerModelAssembler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/v1/admin",
//        consumes = MediaType.APPLICATION_JSON_VALUE,
//        produces = MediaType.APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
//public class AdminController {
//    private final CustomerFacade customerFacade;
//    private final CustomerModelAssembler customerModelAssembler;
//
//    @GetMapping(value = "/user/{id}")
//    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable long id) {
//        CustomerDTO customerDTO = customerFacade.findById(id);
//        return new ResponseEntity<>(customerModelAssembler.toModel(customerDTO), HttpStatus.OK);
//    }
//}
