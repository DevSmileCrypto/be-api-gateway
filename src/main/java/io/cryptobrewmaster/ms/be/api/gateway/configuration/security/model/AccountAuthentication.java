package io.cryptobrewmaster.ms.be.api.gateway.configuration.security.model;

import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.AccountAuthenticationDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
@EqualsAndHashCode(callSuper = true)
public class AccountAuthentication extends AbstractAuthenticationToken {

    private final transient Object principal;
    private final transient Object credentials;

    private final String accountId;

    public AccountAuthentication(AccountAuthenticationDto accountAuthenticationDto) {
        super(accountAuthenticationDto.getAuthenticationRoles());
        this.accountId = accountAuthenticationDto.getAccountId();
        this.principal = accountAuthenticationDto.getAccountId();
        this.credentials = "";
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}
