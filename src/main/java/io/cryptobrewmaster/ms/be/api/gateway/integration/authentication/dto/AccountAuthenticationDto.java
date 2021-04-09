package io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.constants.AuthenticationRole;
import io.cryptobrewmaster.ms.be.library.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountAuthenticationDto {
    @NotNull
    private boolean valid;
    @NotBlank
    private String accountId;
    @NotNull @NotEmpty
    private List<Role> roles;

    public List<AuthenticationRole> getAuthenticationRoles() {
        return roles.stream()
                .map(AuthenticationRole::of)
                .collect(Collectors.toList());
    }
}
