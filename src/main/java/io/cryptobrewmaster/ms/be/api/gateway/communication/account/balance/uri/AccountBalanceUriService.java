package io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.uri;

import java.net.URI;

/**
 * The interface Account balance uri service.
 */
public interface AccountBalanceUriService {

    /**
     * Gets account balance fetch for ui uri.
     *
     * @return the account balance fetch for ui uri
     */
    URI getAccountBalanceFetchForUiUri();

}
