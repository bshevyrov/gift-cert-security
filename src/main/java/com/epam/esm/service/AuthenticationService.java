package com.epam.esm.service;

import com.epam.esm.veiw.dto.AuthenticationRequestDTO;
import com.epam.esm.veiw.dto.AuthenticationResponseDTO;
import com.epam.esm.veiw.dto.CustomerDTO;


public interface AuthenticationService {
    AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO);

}
