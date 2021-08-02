package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.properties.InventoryProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class InventoryUriServiceImpl implements InventoryUriService {

    private final InventoryProperties inventoryProperties;

    @Override
    public URI getAccountCardStateFetchForUiUri() {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAccountCardStateFetchForUi())
                .build()
                .encode()
                .toUri();
    }
}
