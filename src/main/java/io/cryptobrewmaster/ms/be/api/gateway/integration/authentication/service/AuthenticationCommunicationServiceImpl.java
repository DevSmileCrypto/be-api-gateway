package io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.service;

import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.uri.AuthenticationUriService;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.AccountAuthenticationDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.authentication.AuthenticationTokenPairDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.authentication.RegistrationOrLoginDto;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import io.cryptobrewmaster.ms.be.library.exception.InnerServiceException;
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

    private String getMicroServiceName() {
        return MicroServiceName.BE_AUTHENTICATION.getProviderName();
    }

    @Override
    public AuthenticationTokenPairDto hiveKeychainLogin(RegistrationOrLoginDto registrationOrLoginDto) {
        var uri = authenticationUriService.getHiveKeychainLoginUri();
        var httpEntity = new HttpEntity<>(registrationOrLoginDto);

        try {
            log.info("Request to registration or login by hive keychain send to {} ms. {}",
                    getMicroServiceName(), registrationOrLoginDto);
            AuthenticationTokenPairDto authenticationTokenPairDto = authenticationRestTemplate.exchange(
                    uri, POST, httpEntity, AuthenticationTokenPairDto.class
            ).getBody();
            log.info("Response on registration or login by hive keychain from {} ms. {}",
                    getMicroServiceName(), authenticationTokenPairDto);
            return authenticationTokenPairDto;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on registration or login by hive keychain request. " +
                            "%s. Error = %s", getMicroServiceName(), registrationOrLoginDto, e.getMessage())
            );
        }
    }

    @Override
    public String hiveSignerLogin() {
        var uri = authenticationUriService.getHiveSignerLoginUri();

        try {
            log.info("Request to registration or login by hive signer send to {} ms.", getMicroServiceName());
            String loginUrl = authenticationRestTemplate.exchange(uri, GET, HttpEntity.EMPTY, String.class).getBody();
            log.info("Response on registration or login by hive signer from {} ms. {}", getMicroServiceName(), loginUrl);
            return loginUrl;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on registration or login by hive signer request. " +
                            "Error = %s", getMicroServiceName(), e.getMessage())
            );
        }
    }

    @Override
    public String hiveSignerLoginRedirect(MultiValueMap<String, String> queryParams) {
        var uri = authenticationUriService.getHiveSignerLoginRedirectUri(queryParams);

        try {
            log.info("Request to complete registration or login by hive signer from redirect send to {} ms.",
                    getMicroServiceName());
            String redirectUrl = authenticationRestTemplate.exchange(uri, GET, HttpEntity.EMPTY, String.class).getBody();
            log.info("Response on complete registration or login by hive signer from redirect from {} ms. {}",
                    getMicroServiceName(), redirectUrl);
            return redirectUrl;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on complete registration or login by hive signer from redirect request. " +
                            "Error = %s", getMicroServiceName(), e.getMessage())
            );
        }
    }

    @Override
    public AuthenticationTokenPairDto refreshTokenPair(String refreshToken) {
        var uri = authenticationUriService.getTokenPairRefreshUri(refreshToken);

        try {
            log.info("Request to refresh account token pair send to {} ms. Refresh token = {}",
                    getMicroServiceName(), refreshToken);
            AuthenticationTokenPairDto authenticationTokenPairDto = authenticationRestTemplate.exchange(
                    uri, PUT, HttpEntity.EMPTY, AuthenticationTokenPairDto.class
            ).getBody();
            log.info("Response on refresh account token pair from {} ms. {}",
                    getMicroServiceName(), authenticationTokenPairDto);
            return authenticationTokenPairDto;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on refresh account token pair request. " +
                            "Refresh token = %s. Error = %s", getMicroServiceName(), refreshToken, e.getMessage())
            );
        }
    }

    @Override
    public AccountAuthenticationDto validateAccessToken(String accessToken) {
        var uri = authenticationUriService.getAccessTokenValidateUri(accessToken);

        try {
            log.info("Request to validate access token and get account authentication send to {} ms. Access token = {}",
                    getMicroServiceName(), accessToken);
            AccountAuthenticationDto accountAuthenticationDto = authenticationRestTemplate.exchange(
                    uri, GET, HttpEntity.EMPTY, AccountAuthenticationDto.class
            ).getBody();
            log.info("Response on validate access token and get account authentication from {} ms. {}",
                    getMicroServiceName(), accountAuthenticationDto);
            return accountAuthenticationDto;
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on validate access token and get account authentication request. " +
                            "Access token = %s. Error = %s", getMicroServiceName(), accessToken, e.getMessage())
            );
        }
    }

    @Override
    public void logout(String accountId) {
        var uri = authenticationUriService.getLogoutUri(accountId);

        try {
            log.info("Request to logout by account id send to {} ms. Account id = {}", getMicroServiceName(), accountId);
            HttpStatus statusCode = authenticationRestTemplate.exchange(uri, PUT, HttpEntity.EMPTY, String.class).getStatusCode();
            log.info("Response on logout by account id from {} ms. Status code = {}", getMicroServiceName(), statusCode);
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on logout by account id request. " +
                            "Account id = %s. Error = %s", getMicroServiceName(), accountId, e.getMessage())
            );
        }
    }

    @Override
    public void refreshServerProperties() {
        var uri = authenticationUriService.getRefreshServerPropertiesUri();

        try {
            log.info("Request to refresh server properties send to {} ms. ", getMicroServiceName());
            HttpStatus statusCode = authenticationRestTemplate.exchange(uri, POST, HttpEntity.EMPTY, String.class).getStatusCode();
            log.info("Response on refresh server properties from {} ms. Status code = {}", getMicroServiceName(), statusCode);
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on refresh server properties request. " +
                            "Error = %s", getMicroServiceName(), e.getMessage())
            );
        }
    }

}
