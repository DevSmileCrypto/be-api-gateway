package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.uri;

import java.net.URI;

/**
 * The interface Inventory uri service.
 */
public interface InventoryUriService {

    /**
     * Gets account card state fetch for ui uri.
     *
     * @return the account card state fetch for ui uri
     */
    URI getAccountCardStateFetchForUiUri();

}
