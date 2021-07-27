package io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.AccountBalanceUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.criteria.AccountBalanceFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;

/**
 * The interface Account balance communication service.
 */
public interface AccountBalanceCommunicationService {

    /**
     * Fetch all account balance for ui page dto.
     *
     * @param criteriaDto the criteria dto
     * @return the page dto
     */
    PageDto<AccountBalanceUiDto> fetchAllAccountBalanceForUi(AccountBalanceFetchedCriteriaDto criteriaDto);

}
