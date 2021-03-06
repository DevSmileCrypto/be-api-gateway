package io.cryptobrewmaster.ms.be.api.gateway.service.state;

import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.StateUiDto;

/**
 * The interface State service.
 */
public interface StateService {

    /**
     * Gets state.
     *
     * @param authentication the authentication
     * @return the state
     */
    StateUiDto getState(AccountAuthentication authentication);

}
