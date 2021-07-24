package io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.uri;

import io.cryptobrewmaster.ms.be.library.constants.GatewayType;
import org.springframework.util.MultiValueMap;

import java.net.URI;

/**
 * The interface Authentication uri service.
 */
public interface AuthenticationUriService {

    /**
     * Gets hive keychain login uri.
     *
     * @param type the type
     * @return the hive keychain login uri
     */
    URI getHiveKeychainLoginUri(GatewayType type);

    /**
     * Gets hive signer login uri.
     *
     * @param type the type
     * @return the hive signer login uri
     */
    URI getHiveSignerLoginUri(GatewayType type);

    /**
     * Gets hive signer login redirect uri.
     *
     * @param queryParams the query params
     * @param type        the type
     * @return the hive signer login redirect uri
     */
    URI getHiveSignerLoginRedirectUri(MultiValueMap<String, String> queryParams, GatewayType type);

    /**
     * Gets refresh token pair uri.
     *
     * @param refreshToken the refresh token
     * @param type         the type
     * @return the refresh token pair uri
     */
    URI getRefreshTokenPairUri(String refreshToken, GatewayType type);

    /**
     * Gets access token validate uri.
     *
     * @param accessToken the access token
     * @param type        the type
     * @return the access token validate uri
     */
    URI getAccessTokenValidateUri(String accessToken, GatewayType type);

    /**
     * Gets logout uri.
     *
     * @param accountId the account id
     * @param type      the type
     * @return the logout uri
     */
    URI getLogoutUri(String accountId, GatewayType type);

}
