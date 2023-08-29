package com.epam.esm.service.impl;

import com.epam.esm.security.jwt.JwtTokenProvider;
import com.epam.esm.service.AuthenticationService;
import com.epam.esm.veiw.dto.AuthenticationRequestDTO;
import com.epam.esm.veiw.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO) {
        String username = authenticationRequestDTO.getUsername();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, authenticationRequestDTO.getPassword()));
        String token = jwtTokenProvider.createToken(username);
        return new AuthenticationResponseDTO(username, token);
    }


}
