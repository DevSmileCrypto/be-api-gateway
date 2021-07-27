package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.AccountBeerCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.criteria.AccountBeerCardFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.AccountResourceCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.criteria.AccountResourceCardFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;

/**
 * The interface Inventory communication service.
 */
public interface InventoryCommunicationService {

    /**
     * Fetch all account resource card for ui page dto.
     *
     * @param criteriaDto the criteria dto
     * @return the page dto
     */
    PageDto<AccountResourceCardUiDto> fetchAllAccountResourceCardForUi(AccountResourceCardFetchedCriteriaDto criteriaDto);

    /**
     * Fetch all account beer card for ui page dto.
     *
     * @param criteriaDto the criteria dto
     * @return the page dto
     */
    PageDto<AccountBeerCardUiDto> fetchAllAccountBeerCardForUi(AccountBeerCardFetchedCriteriaDto criteriaDto);

}
