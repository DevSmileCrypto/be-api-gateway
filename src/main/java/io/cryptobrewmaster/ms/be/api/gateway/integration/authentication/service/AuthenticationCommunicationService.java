package io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.service;

import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.AccountAuthenticationDto;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.AuthenticationTokenPairDto;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.RegistrationOrLoginDto;
import org.springframework.util.MultiValueMap;

/**
 * The interface Authentication communication service.
 */
public interface AuthenticationCommunicationService {

    /**
     * Hive keychain login authentication token pair dto.
     *
     * @param registrationOrLoginDto the registration or login dto
     * @return the authentication token pair dto
     */
    AuthenticationTokenPairDto hiveKeychainLogin(RegistrationOrLoginDto registrationOrLoginDto);

    /**
     * Hive signer login string.
     *
     * @return the string
     */
    String hiveSignerLogin();

    /**
     * Hive signer login redirect string.
     *
     * @param queryParams the query params
     * @return the string
     */
    String hiveSignerLoginRedirect(MultiValueMap<String, String> queryParams);

    /**
     * Token pair refresh authentication token pair dto.
     *
     * @param refreshToken the refresh token
     * @return the authentication token pair dto
     */
    AuthenticationTokenPairDto tokenPairRefresh(String refreshToken);

    /**
     * Access token validate account authentication dto.
     *
     * @param accessToken the access token
     * @return the account authentication dto
     */
    AccountAuthenticationDto accessTokenValidate(String accessToken);

    /**
     * Logout.
     *
     * @param accountId the account id
     */
    void logout(String accountId);
}
