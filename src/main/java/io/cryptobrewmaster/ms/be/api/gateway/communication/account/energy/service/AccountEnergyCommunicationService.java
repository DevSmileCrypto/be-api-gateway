package io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.AccountEnergyUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.criteria.AccountEnergyFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;

/**
 * The interface Account energy communication service.
 */
public interface AccountEnergyCommunicationService {

    /**
     * Fetch all account energy for ui page dto.
     *
     * @param criteriaDto the criteria dto
     * @return the page dto
     */
    PageDto<AccountEnergyUiDto> fetchAllAccountEnergyForUi(AccountEnergyFetchedCriteriaDto criteriaDto);

}
