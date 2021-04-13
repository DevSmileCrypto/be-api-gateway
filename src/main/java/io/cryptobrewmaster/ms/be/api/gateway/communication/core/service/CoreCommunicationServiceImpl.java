package io.cryptobrewmaster.ms.be.api.gateway.communication.core.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.core.uri.CoreUriService;
import io.cryptobrewmaster.ms.be.library.communication.BaseCommunicationService;
import io.cryptobrewmaster.ms.be.library.communication.model.RequestLog;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CoreCommunicationServiceImpl extends BaseCommunicationService implements CoreCommunicationService {

    private final CoreUriService coreUriService;

    public CoreCommunicationServiceImpl(RestTemplate coreRestTemplate,
                                        CoreUriService coreUriService) {
        super(coreRestTemplate);
        this.coreUriService = coreUriService;
    }

    String getMicroServiceName() {
        return MicroServiceName.BE_CORE.getProviderName();
    }

    @Override
    public void refreshServerProperties() {
        var logArgs = List.<Object>of(getMicroServiceName());
        performRequestWithoutResponse(
                coreUriService.getRefreshServerPropertiesUri(),
                HttpMethod.POST,
                new RequestLog(
                        "Request to refresh server properties send to %s ms.", logArgs,
                        "Response on refresh server properties from %s ms.", logArgs,
                        "No response from %s ms on refresh server properties request.", logArgs
                )
        );
    }

}
