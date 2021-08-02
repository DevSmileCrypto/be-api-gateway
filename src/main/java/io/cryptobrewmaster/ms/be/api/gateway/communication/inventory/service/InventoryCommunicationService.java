package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardStateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardUiFetchedCriteriaDto;

/**
 * The interface Inventory communication service.
 */
public interface InventoryCommunicationService {

    /**
     * Gets account card state for ui.
     *
     * @param criteriaDto the criteria dto
     * @return the account card state for ui
     */
    AccountCardStateUiDto getAccountCardStateForUi(AccountCardUiFetchedCriteriaDto criteriaDto);

}
