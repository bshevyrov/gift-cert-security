package com.epam.esm.veiw.facade.impl;

import com.epam.esm.service.AuthenticationService;
import com.epam.esm.veiw.dto.AuthenticationRequestDTO;
import com.epam.esm.veiw.dto.AuthenticationResponseDTO;
import com.epam.esm.veiw.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final AuthenticationService authenticationService;

    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO) {
        return authenticationService.login(authenticationRequestDTO);
    }

}
