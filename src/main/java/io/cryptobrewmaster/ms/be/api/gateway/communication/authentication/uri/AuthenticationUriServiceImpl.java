package io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.properties.AuthenticationProperties;
import io.cryptobrewmaster.ms.be.library.constants.GatewayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AuthenticationUriServiceImpl implements AuthenticationUriService {

    private static final String TYPE_PARAM = "type";

    private final AuthenticationProperties authenticationProperties;

    @Override
    public URI getHiveKeychainLoginUri(GatewayType type) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getHiveKeychain().getLogin())
                .queryParam(TYPE_PARAM, type)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getHiveSignerLoginUri(GatewayType type) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getHiveSigner().getLogin())
                .queryParam(TYPE_PARAM, type)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getHiveSignerLoginRedirectUri(MultiValueMap<String, String> queryParams, GatewayType type) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getHiveSigner().getLoginRedirect())
                .queryParams(queryParams)
                .queryParam(TYPE_PARAM, type)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getRefreshTokenPairUri(String refreshToken, GatewayType type) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getToken().getRefresh())
                .queryParam(TYPE_PARAM, type)
                .buildAndExpand(refreshToken)
                .encode()
                .toUri();
    }

    @Override
    public URI getAccessTokenValidateUri(String accessToken, GatewayType type) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getToken().getValidate())
                .queryParam(TYPE_PARAM, type)
                .buildAndExpand(accessToken)
                .encode()
                .toUri();
    }

    @Override
    public URI getLogoutUri(String accountId, GatewayType type) {
        return UriComponentsBuilder.fromUriString(authenticationProperties.getUri())
                .path(authenticationProperties.getPath().getLogout())
                .queryParam(TYPE_PARAM, type)
                .buildAndExpand(accountId)
                .encode()
                .toUri();
    }

}
