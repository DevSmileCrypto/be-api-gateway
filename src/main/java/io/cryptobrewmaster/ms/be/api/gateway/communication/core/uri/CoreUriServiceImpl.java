package io.cryptobrewmaster.ms.be.api.gateway.communication.core.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.core.properties.CoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class CoreUriServiceImpl implements CoreUriService {

    private final CoreProperties coreProperties;

    @Override
    public URI getRefreshServerPropertiesUri() {
        return UriComponentsBuilder.fromUriString(coreProperties.getUri())
                .path(coreProperties.getPath().getRefreshServerProperties())
                .build()
                .encode()
                .toUri();
    }
}
