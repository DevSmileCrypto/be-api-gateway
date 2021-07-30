package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.AccountBeerCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.criteria.AccountBeerCardUiFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.AccountResourceCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.criteria.AccountResourceCardUiFetchedCriteriaDto;

import java.util.List;

/**
 * The interface Inventory communication service.
 */
public interface InventoryCommunicationService {

    /**
     * Fetch all account resource card for ui list.
     *
     * @param criteriaDto the criteria dto
     * @return the list
     */
    List<AccountResourceCardUiDto> fetchAllAccountResourceCardForUi(AccountResourceCardUiFetchedCriteriaDto criteriaDto);

    /**
     * Fetch all account beer card for ui list.
     *
     * @param criteriaDto the criteria dto
     * @return the list
     */
    List<AccountBeerCardUiDto> fetchAllAccountBeerCardForUi(AccountBeerCardUiFetchedCriteriaDto criteriaDto);

}
