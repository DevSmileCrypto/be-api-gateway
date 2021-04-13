package io.cryptobrewmaster.ms.be.api.gateway.communication.account.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.uri.AccountUriService;
import io.cryptobrewmaster.ms.be.library.communication.BaseCommunicationService;
import io.cryptobrewmaster.ms.be.library.communication.model.RequestLog;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountCommunicationServiceImpl extends BaseCommunicationService implements AccountCommunicationService {

    private final AccountUriService accountUriService;

    public AccountCommunicationServiceImpl(RestTemplate accountRestTemplate,
                                           AccountUriService accountUriService) {
        super(accountRestTemplate);
        this.accountUriService = accountUriService;
    }

    String getMicroServiceName() {
        return MicroServiceName.BE_ACCOUNT.getProviderName();
    }

    @Override
    public void refreshServerProperties() {
        var logArgs = List.<Object>of(getMicroServiceName());
        performRequestWithoutResponse(
                accountUriService.getRefreshServerPropertiesUri(),
                HttpMethod.POST,
                new RequestLog(
                        "Request to refresh server properties send to %s ms.", logArgs,
                        "Response on refresh server properties from %s ms.", logArgs,
                        "No response from %s ms on refresh server properties request.", logArgs
                )
        );
    }

}
