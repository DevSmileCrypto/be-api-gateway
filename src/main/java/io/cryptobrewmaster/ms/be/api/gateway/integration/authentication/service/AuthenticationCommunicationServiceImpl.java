package io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.service;

import io.cryptobrewmaster.ms.be.api.gateway.exception.InnerServiceException;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.AccountAuthenticationDto;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.AuthenticationTokenPairDto;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.RegistrationOrLoginDto;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.uri.AuthenticationUriService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationCommunicationServiceImpl implements AuthenticationCommunicationService {

    private final RestTemplate authenticationRestTemplate;
    private final AuthenticationUriService authenticationUriService;

    @Override
    public AuthenticationTokenPairDto hiveKeychainLogin(RegistrationOrLoginDto registrationOrLoginDto) {
        var uri = authenticationUriService.getHiveKeychainLoginUri();

        try {
            log.info("Request to registration or login by hive keychain send to my-account ms. {}", registrationOrLoginDto);
            AuthenticationTokenPairDto authenticationTokenPairDto = authenticationRestTemplate.exchange(
                    uri, POST, HttpEntity.EMPTY, AuthenticationTokenPairDto.class
            ).getBody();
            log.info("Response on registration or login by hive keychain from my-account ms. {}", authenticationTokenPairDto);
            return authenticationTokenPairDto;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from my-account ms on registration or login by hive keychain request. " +
                            "%s. Error = %s", registrationOrLoginDto, e.getMessage())
            );
        }
    }

    @Override
    public String hiveSignerLogin() {
        var uri = authenticationUriService.getHiveSignerLoginUri();

        try {
            log.info("Request to registration or login by hive signer send to my-account ms.");
            String loginUrl = authenticationRestTemplate.exchange(uri, GET, HttpEntity.EMPTY, String.class).getBody();
            log.info("Response on registration or login by hive signer from my-account ms. {}", loginUrl);
            return loginUrl;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from my-account ms on registration or login by hive signer request. " +
                            "Error = %s", e.getMessage())
            );
        }
    }

    @Override
    public String hiveSignerLoginRedirect(MultiValueMap<String, String> queryParams) {
        var uri = authenticationUriService.getHiveSignerLoginRedirectUri(queryParams);

        try {
            log.info("Request to complete registration or login by hive signer from redirect send to my-account ms.");
            String redirectUrl = authenticationRestTemplate.exchange(uri, GET, HttpEntity.EMPTY, String.class).getBody();
            log.info("Response on complete registration or login by hive signer from redirect from my-account ms. {}", redirectUrl);
            return redirectUrl;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from my-account ms on complete registration or login by hive signer from redirect request. " +
                            "Error = %s", e.getMessage())
            );
        }
    }

    @Override
    public AuthenticationTokenPairDto tokenPairRefresh(String refreshToken) {
        var uri = authenticationUriService.getTokenPairRefreshUri(refreshToken);

        try {
            log.info("Request to refresh account token pair send to my-account ms. Refresh token = {}", refreshToken);
            AuthenticationTokenPairDto authenticationTokenPairDto = authenticationRestTemplate.exchange(
                    uri, PUT, HttpEntity.EMPTY, AuthenticationTokenPairDto.class
            ).getBody();
            log.info("Response on refresh account token pair from my-account ms. {}", authenticationTokenPairDto);
            return authenticationTokenPairDto;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from my-account ms on refresh account token pair request. " +
                            "Refresh token = %s. Error = %s", refreshToken, e.getMessage())
            );
        }
    }

    @Override
    public AccountAuthenticationDto accessTokenValidate(String accessToken) {
        var uri = authenticationUriService.getAccessTokenValidateUri(accessToken);

        try {
            log.info("Request to validate access token and get account authentication send to my-account ms. Access token = {}",
                    accessToken);
            AccountAuthenticationDto accountAuthenticationDto = authenticationRestTemplate.exchange(
                    uri, GET, HttpEntity.EMPTY, AccountAuthenticationDto.class
            ).getBody();
            log.info("Response on validate access token and get account authentication from my-account ms. {}", accountAuthenticationDto);
            return accountAuthenticationDto;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from my-account ms on validate access token and get account authentication request. " +
                            "Access token = %s. Error = %s", accessToken, e.getMessage())
            );
        }
    }

    @Override
    public void logout(String accountId) {
        var uri = authenticationUriService.getLogoutUri(accountId);

        try {
            log.info("Request to logout by account id send to my-account ms. Account id = {}", accountId);
            HttpStatus statusCode = authenticationRestTemplate.exchange(uri, PUT, HttpEntity.EMPTY, String.class).getStatusCode();
            log.info("Response on logout by account id from my-account ms. Status code = {}", statusCode);
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from my-account ms on logout by account id request. " +
                            "Account id = %s. Error = %s", accountId, e.getMessage())
            );
        }
    }
}
