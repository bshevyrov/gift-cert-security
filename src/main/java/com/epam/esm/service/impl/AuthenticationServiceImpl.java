package com.epam.esm.service.impl;

import com.epam.esm.exception.auth.InvalidPasswordException;
import com.epam.esm.exception.auth.InvalidUsernameException;
import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.repository.CustomerRepository;
import com.epam.esm.security.jwt.JwtTokenProvider;
import com.epam.esm.service.AuthenticationService;
import com.epam.esm.veiw.dto.AuthenticationRequestDTO;
import com.epam.esm.veiw.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MessageSource messageSource;


    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO) {
        String username = authenticationRequestDTO.getUsername();
        CustomerEntity customerEntity = customerRepository.findByUsername(username).orElseThrow(
                () -> new InvalidUsernameException(messageSource.getMessage("invalid.username.exception",
                        new Object[]{username},
                        LocaleContextHolder.getLocale()))
        );

        if (!passwordEncoder.matches(authenticationRequestDTO.getPassword(), customerEntity.getPassword())) {
            throw new InvalidPasswordException(messageSource.getMessage("invalid.password.exception",
                    new Object[]{},
                    LocaleContextHolder.getLocale()));
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, authenticationRequestDTO.getPassword()));

        String token = jwtTokenProvider.createToken(username);
        return new AuthenticationResponseDTO(username, token);
    }
}
