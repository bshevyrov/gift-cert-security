package com.epam.esm.service;

import com.epam.esm.persistence.entity.AbstractAuditEntity;
import com.epam.esm.security.jwt.JwtUser;
import io.jsonwebtoken.JwtException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public interface BaseService<E extends AbstractAuditEntity> {
    E create(E entity);

    E findById(long id);

    Page<E> findAll(Pageable pageable);

    E update(E entity);

    E delete(long id);

    default boolean isAuthenticatedUser(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
                || isRoleUserAndIdBelongsToUser(id, authentication, authorities));
    }

    default boolean isRoleUserAndIdBelongsToUser(long id, Authentication authentication, Collection<? extends GrantedAuthority> authorities) {
        return authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))
                && id == ((JwtUser) authentication.getPrincipal()).getId();
    }
}
