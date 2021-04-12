package io.cryptobrewmaster.ms.be.api.gateway.communication.account.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.properties.AccountProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AccountUriServiceImpl implements AccountUriService {

    private final AccountProperties accountProperties;

    @Override
    public URI getRefreshServerPropertiesUri() {
        return UriComponentsBuilder.fromUriString(accountProperties.getUri())
                .path(accountProperties.getPath().getRefreshServerProperties())
                .build()
                .encode()
                .toUri();
    }
}
