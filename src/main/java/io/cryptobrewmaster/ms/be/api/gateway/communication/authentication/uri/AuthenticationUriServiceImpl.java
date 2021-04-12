package io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.properties.AuthenticationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AuthenticationUriServiceImpl implements AuthenticationUriService {

    private final AuthenticationProperties authenticationProperties;

    @Override
    public URI getHiveKeychainLoginUri() {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getHiveKeychain().getLogin())
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getHiveSignerLoginUri() {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getHiveSigner().getLogin())
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getHiveSignerLoginRedirectUri(MultiValueMap<String, String> queryParams) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getHiveSigner().getLoginRedirect())
                .queryParams(queryParams)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getRefreshTokenPairUri(String refreshToken) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getToken().getRefresh())
                .buildAndExpand(refreshToken)
                .encode()
                .toUri();
    }

    @Override
    public URI getAccessTokenValidateUri(String accessToken) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getToken().getValidate())
                .buildAndExpand(accessToken)
                .encode()
                .toUri();
    }

    @Override
    public URI getLogoutUri(String accountId) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getLogout())
                .buildAndExpand(accountId)
                .encode()
                .toUri();
    }

    @Override
    public URI getRefreshServerPropertiesUri() {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getRefreshServerProperties())
                .build()
                .encode()
                .toUri();
    }
}
