package io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.properties.AccountEnergyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AccountEnergyUriServiceImpl implements AccountEnergyUriService {

    private final AccountEnergyProperties accountEnergyProperties;

    @Override
    public URI getAccountEnergyFetchForUiUri() {
        return UriComponentsBuilder.fromUriString(accountEnergyProperties.getUri())
                .path(accountEnergyProperties.getPath().getAccountEnergyFetchForUi())
                .build()
                .encode()
                .toUri();
    }

}
