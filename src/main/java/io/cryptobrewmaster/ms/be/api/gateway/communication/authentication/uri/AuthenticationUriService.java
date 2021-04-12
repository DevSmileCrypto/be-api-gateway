package io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.uri;

import org.springframework.util.MultiValueMap;

import java.net.URI;

/**
 * The interface Authentication uri service.
 */
public interface AuthenticationUriService {

    /**
     * Gets hive keychain login uri.
     *
     * @return the hive keychain login uri
     */
    URI getHiveKeychainLoginUri();

    /**
     * Gets hive signer login uri.
     *
     * @return the hive signer login uri
     */
    URI getHiveSignerLoginUri();

    /**
     * Gets hive signer login redirect uri.
     *
     * @param queryParams the query params
     * @return the hive signer login redirect uri
     */
    URI getHiveSignerLoginRedirectUri(MultiValueMap<String, String> queryParams);

    /**
     * Gets token pair refresh uri.
     *
     * @param refreshToken the refresh token
     * @return the token pair refresh uri
     */
    URI getRefreshTokenPairUri(String refreshToken);

    /**
     * Gets access token validate uri.
     *
     * @param accessToken the access token
     * @return the access token validate uri
     */
    URI getAccessTokenValidateUri(String accessToken);

    /**
     * Gets logout uri.
     *
     * @param accountId the account id
     * @return the logout uri
     */
    URI getLogoutUri(String accountId);

    /**
     * Gets refresh server properties uri.
     *
     * @return the refresh server properties uri
     */
    URI getRefreshServerPropertiesUri();

}
