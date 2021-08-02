package io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.AccountEnergyUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.criteria.AccountEnergyFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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

    /**
     * Fetch async all account energy for ui completable future.
     *
     * @param criteriaDto the criteria dto
     * @param executor    the executor
     * @return the completable future
     */
    default CompletableFuture<PageDto<AccountEnergyUiDto>> fetchAsyncAllAccountEnergyForUi(AccountEnergyFetchedCriteriaDto criteriaDto,
                                                                                           Executor executor) {
        return CompletableFuture.supplyAsync(() -> fetchAllAccountEnergyForUi(criteriaDto), executor);
    }

}
