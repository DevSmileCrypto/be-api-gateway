package io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.dto.AccountAuthenticationDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.authentication.AuthenticationTokenPairDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.authentication.RegistrationOrLoginDto;
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
    AuthenticationTokenPairDto loginHiveKeychain(RegistrationOrLoginDto registrationOrLoginDto);

    /**
     * Hive signer login string.
     *
     * @return the string
     */
    String loginHiveSigner();

    /**
     * Hive signer login redirect string.
     *
     * @param queryParams the query params
     * @return the string
     */
    String redirectHiveSignerLogin(MultiValueMap<String, String> queryParams);

    /**
     * Refresh token pair authentication token pair dto.
     *
     * @param refreshToken the refresh token
     * @return the authentication token pair dto
     */
    AuthenticationTokenPairDto refreshTokenPair(String refreshToken);

    /**
     * Validate access token account authentication dto.
     *
     * @param accessToken the access token
     * @return the account authentication dto
     */
    AccountAuthenticationDto validateAccessToken(String accessToken);

    /**
     * Logout.
     *
     * @param accountId the account id
     */
    void logout(String accountId);

}
