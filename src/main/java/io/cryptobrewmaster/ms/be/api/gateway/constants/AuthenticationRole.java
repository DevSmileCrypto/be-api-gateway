package io.cryptobrewmaster.ms.be.api.gateway.constants;

import io.cryptobrewmaster.ms.be.library.constants.Role;
import org.springframework.security.core.GrantedAuthority;

public enum AuthenticationRole implements GrantedAuthority {
    USER, ADMIN;

    public static AuthenticationRole of(Role role) {
        return AuthenticationRole.valueOf(role.name());
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
