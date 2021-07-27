package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.uri;

import java.net.URI;

/**
 * The interface Inventory uri service.
 */
public interface InventoryUriService {

    /**
     * Gets account resource card fetch for ui uri.
     *
     * @return the account resource card fetch for ui uri
     */
    URI getAccountResourceCardFetchForUiUri();

    /**
     * Gets account beer card fetch for ui uri.
     *
     * @return the account beer card fetch for ui uri
     */
    URI getAccountBeerCardFetchForUiUri();

}
