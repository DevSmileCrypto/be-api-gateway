package io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.dto.AccountAuthenticationDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.dto.AuthenticationTokenPairDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.dto.RegistrationOrLoginDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.uri.AuthenticationUriService;
import io.cryptobrewmaster.ms.be.library.communication.BaseCommunicationService;
import io.cryptobrewmaster.ms.be.library.communication.model.RequestLog;
import io.cryptobrewmaster.ms.be.library.constants.GatewayType;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class AuthenticationCommunicationServiceImpl extends BaseCommunicationService implements AuthenticationCommunicationService {

    private final AuthenticationUriService authenticationUriService;

    public AuthenticationCommunicationServiceImpl(RestTemplate authenticationRestTemplate,
                                                  AuthenticationUriService authenticationUriService) {
        super(authenticationRestTemplate);
        this.authenticationUriService = authenticationUriService;
    }

    String getMicroServiceName() {
        return MicroServiceName.BE_AUTHENTICATION.getProviderName();
    }

    GatewayType getGatewayType() {
        return GatewayType.API;
    }

    @Override
    public AuthenticationTokenPairDto loginHiveKeychain(RegistrationOrLoginDto registrationOrLoginDto) {
        return performRequestWithResponse(
                authenticationUriService.getHiveKeychainLoginUri(getGatewayType()),
                HttpMethod.POST, registrationOrLoginDto,
                AuthenticationTokenPairDto.class,
                new RequestLog(
                        "Request to registration or login by hive keychain send to %s ms. %s",
                        List.of(getMicroServiceName(), registrationOrLoginDto),
                        "Response on registration or login by hive keychain from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on registration or login by hive keychain request. %s.",
                        List.of(getMicroServiceName(), registrationOrLoginDto)
                )
        );
    }

    @Override
    public String loginHiveSigner() {
        var logArgs = List.<Object>of(getMicroServiceName());
        return performRequestWithResponse(
                authenticationUriService.getHiveSignerLoginUri(getGatewayType()),
                HttpMethod.GET,
                String.class,
                new RequestLog(
                        "Request to registration or login by hive signer send to %s ms.", logArgs,
                        "Response on registration or login by hive signer from %s ms.", logArgs,
                        "No response from %s ms on registration or login by hive signer request.", logArgs
                )
        );
    }

    @Override
    public String redirectHiveSignerLogin(MultiValueMap<String, String> queryParams) {
        var logArgs = List.<Object>of(getMicroServiceName());
        return performRequestWithResponse(
                authenticationUriService.getHiveSignerLoginRedirectUri(queryParams, getGatewayType()),
                HttpMethod.GET,
                String.class,
                new RequestLog(
                        "Request to complete registration or login by hive signer from redirect send to %s ms.", logArgs,
                        "Response on complete registration or login by hive signer from redirect from %s ms.", logArgs,
                        "No response from %s ms on complete registration or login by hive signer from redirect request.", logArgs
                )
        );
    }

    @Override
    public AuthenticationTokenPairDto refreshTokenPair(String refreshToken) {
        return performRequestWithResponse(
                authenticationUriService.getRefreshTokenPairUri(refreshToken, getGatewayType()),
                HttpMethod.PUT,
                AuthenticationTokenPairDto.class,
                new RequestLog(
                        "Request to refresh account token pair send to %s ms. Refresh token = %s",
                        List.of(getMicroServiceName(), refreshToken),
                        "Response on refresh account token pair from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on refresh account token pair request. Refresh token = %s.",
                        List.of(getMicroServiceName(), refreshToken)
                )
        );
    }

    @Override
    public AccountAuthenticationDto validateAccessToken(String accessToken) {
        return performRequestWithResponse(
                authenticationUriService.getAccessTokenValidateUri(accessToken, getGatewayType()),
                HttpMethod.GET,
                AccountAuthenticationDto.class,
                new RequestLog(
                        "Request to validate access token and get account authentication send to %s ms. Access token = %s",
                        List.of(getMicroServiceName(), accessToken),
                        "Response on validate access token and get account authentication from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on validate access token and get account authentication request. Access token = %s.",
                        List.of(getMicroServiceName(), accessToken)
                )
        );
    }

    @Override
    public void logout(String accountId) {
        performRequestWithoutResponse(
                authenticationUriService.getLogoutUri(accountId, getGatewayType()),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to logout by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on logout by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on logout by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

}
