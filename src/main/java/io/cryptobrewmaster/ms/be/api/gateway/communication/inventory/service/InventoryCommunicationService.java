package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardStateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardUiFetchedCriteriaDto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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

    /**
     * Gets async account card state for ui.
     *
     * @param criteriaDto the criteria dto
     * @param executor    the executor
     * @return the async account card state for ui
     */
    default CompletableFuture<AccountCardStateUiDto> getAsyncAccountCardStateForUi(AccountCardUiFetchedCriteriaDto criteriaDto,
                                                                                   Executor executor) {
        return CompletableFuture.supplyAsync(() -> getAccountCardStateForUi(criteriaDto), executor);
    }

}
