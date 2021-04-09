package io.cryptobrewmaster.ms.be.api.gateway.integration.account.service;

import io.cryptobrewmaster.ms.be.api.gateway.integration.account.uri.AccountUriService;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import io.cryptobrewmaster.ms.be.library.exception.InnerServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountCommunicationServiceImpl implements AccountCommunicationService {

    private final RestTemplate accountRestTemplate;
    private final AccountUriService accountUriService;

    private String getMicroServiceName() {
        return MicroServiceName.BE_ACCOUNT.getProviderName();
    }

    @Override
    public void refreshServerProperties() {
        var uri = accountUriService.getRefreshServerPropertiesUri();

        try {
            log.info("Request to refresh server properties send to {} ms. ", getMicroServiceName());
            HttpStatus statusCode = accountRestTemplate.exchange(uri, POST, HttpEntity.EMPTY, String.class).getStatusCode();
            log.info("Response on refresh server properties from {} ms. Status code = {}", getMicroServiceName(), statusCode);
        } catch (ResourceAccessException e) {
            throw new InnerServiceException(
                    String.format("No response from %s ms on refresh server properties request. " +
                            "Error = %s", getMicroServiceName(), e.getMessage())
            );
        }
    }

}
