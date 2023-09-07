package com.epam.esm.veiw.controller;

import com.epam.esm.veiw.dto.AuthenticationRequestDTO;
import com.epam.esm.veiw.dto.AuthenticationResponseDTO;
import com.epam.esm.veiw.dto.CustomerDTO;
import com.epam.esm.veiw.facade.AuthenticationFacade;
import com.epam.esm.veiw.facade.CustomerFacade;
import com.epam.esm.veiw.model.CustomerModel;
import com.epam.esm.veiw.model.CustomerModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationFacade authenticationFacade;
    private final CustomerFacade customerFacade;
    private final CustomerModelAssembler customerModelAssembler;

    @PostMapping(value = "login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        AuthenticationResponseDTO authenticationResponseDTO = authenticationFacade.login(authenticationRequestDTO);
        return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
    }

    @PostMapping(value = "signup")
    public ResponseEntity<CustomerModel> register(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerModelAssembler.toModel(
                customerFacade.create(customerDTO)), HttpStatus.OK);
    }
}