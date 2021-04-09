package io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.authentication.model.jwt.JwtTokenPair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationTokenPairDto {
    @NotBlank
    private String accessToken;
    @NotNull
    private Long expirationAccessTokenDate;
    @NotBlank
    private String refreshToken;
    @NotNull
    private Long expirationRefreshTokenDate;
}
