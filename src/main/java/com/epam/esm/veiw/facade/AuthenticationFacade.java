package com.epam.esm.veiw.facade;

import com.epam.esm.veiw.dto.AuthenticationRequestDTO;
import com.epam.esm.veiw.dto.AuthenticationResponseDTO;

public interface AuthenticationFacade {
    AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO);

}
