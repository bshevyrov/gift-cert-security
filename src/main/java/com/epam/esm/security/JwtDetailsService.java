package com.epam.esm.security;

import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.security.jwt.JwtUserFactory;
import com.epam.esm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Used to generate {@link com.epam.esm.security.jwt.JwtUser}
 */
@Service
@RequiredArgsConstructor
public class JwtDetailsService implements UserDetailsService {
    private final CustomerService customerService;

    /**
     * Finds customer by username and transforms him into {@link com.epam.esm.security.jwt.JwtUser}
     *
     * @param username the username identifying the user whose data is required.
     * @return {@link com.epam.esm.security.jwt.JwtUser}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        CustomerEntity customerEntity = customerService.findByUsername(username);
        return JwtUserFactory.create(customerEntity);
    }
}
