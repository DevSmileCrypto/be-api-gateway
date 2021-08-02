package io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.AccountBalanceUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.criteria.AccountBalanceFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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

    /**
     * Fetch async all account balance for ui completable future.
     *
     * @param criteriaDto the criteria dto
     * @param executor    the executor
     * @return the completable future
     */
    default CompletableFuture<PageDto<AccountBalanceUiDto>> fetchAsyncAllAccountBalanceForUi(AccountBalanceFetchedCriteriaDto criteriaDto,
                                                                                             Executor executor) {
        return CompletableFuture.supplyAsync(() -> fetchAllAccountBalanceForUi(criteriaDto), executor);
    }

}
