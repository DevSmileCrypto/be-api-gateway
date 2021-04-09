package io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountAuthenticationDto {
    @NotNull
    private boolean valid;
    @NotBlank
    private String accountId;
    @NotNull
    private List<Role> roles;
}
