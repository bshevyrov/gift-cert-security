package com.epam.esm.security.jwt;

import com.epam.esm.persistence.entity.CustomerEntity;
import com.epam.esm.persistence.entity.RoleEntity;
import com.epam.esm.persistence.entity.type.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class creates {@link JwtUser} from {@link CustomerEntity}
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    /**
     * Transforms customer entity to jwtUser
     *
     * @param customerEntity value
     * @return {@link  JwtUser}
     */
    public static JwtUser create(CustomerEntity customerEntity) {
        return new JwtUser(
                customerEntity.getId(),
                customerEntity.getUsername(),
                customerEntity.getPassword(),
                customerEntity.getStatus().equals(Status.ACTIVE),
                mapToGrantedAuthority(new ArrayList<>(customerEntity.getRoleEntities()))
        );
    }

    /**
     * Transforms customer roles to {@link GrantedAuthority}
     *
     * @param roleEntities request parameter.
     * @return list GrantedAuthority
     */
    private static List<GrantedAuthority> mapToGrantedAuthority(List<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                .collect(Collectors.toList());
    }
}
