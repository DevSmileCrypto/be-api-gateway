package io.cryptobrewmaster.ms.be.api.gateway.communication.state.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.state.properties.StateProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class StateUriServiceImpl implements StateUriService {

    private final StateProperties stateProperties;

    @Override
    public URI getRefreshServerPropertiesUri() {
        return UriComponentsBuilder.fromUriString(stateProperties.getUri())
                .path(stateProperties.getPath().getRefreshServerProperties())
                .build()
                .encode()
                .toUri();
    }
}
