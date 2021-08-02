package io.cryptobrewmaster.ms.be.api.gateway.service.state;

import com.spotify.futures.CompletableFutures;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.criteria.AccountBalanceFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.service.AccountBalanceCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.criteria.AccountEnergyFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.service.AccountEnergyCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardUiFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service.InventoryCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.AccountBuildingStateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.service.ProductionBuildingCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.StateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.account.AccountStateUiDto;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;
import io.cryptobrewmaster.ms.be.library.exception.InnerServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static io.cryptobrewmaster.ms.be.library.constants.card.CardStatus.ACTIVE;

@Slf4j
@RequiredArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final AccountBalanceCommunicationService accountBalanceCommunicationService;
    private final AccountEnergyCommunicationService accountEnergyCommunicationService;
    private final InventoryCommunicationService inventoryCommunicationService;

    private final ProductionBuildingCommunicationService productionBuildingCommunicationService;

    private final ThreadPoolTaskExecutor cachedMDCThreadPoolTaskExecutor;

    @Override
    public StateUiDto getState(AccountAuthentication authentication) {
        try {
            return CompletableFutures.combine(
                            getAsyncAccountState(authentication.getAccountId()),
                            getAsyncBuildingState(authentication.getAccountId()),
                            StateUiDto::new
                    )
                    .toCompletableFuture()
                    .get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new InnerServiceException(
                    String.format("Error by interrupted exception while on get state by account id = %s. Error = %s",
                            authentication.getAccountId(), e.getMessage())
            );
        } catch (Exception e) {
            throw new InnerServiceException(
                    String.format("Error while on get state by account id = %s. Error = %s",
                            authentication.getAccountId(), e.getMessage())
            );
        }
    }

    private CompletableFuture<AccountBuildingStateUiDto> getAsyncBuildingState(String accountId) {
        return productionBuildingCommunicationService.getAsyncAccountBuildingStateForUi(
                accountId, cachedMDCThreadPoolTaskExecutor
        );
    }

    private CompletableFuture<AccountStateUiDto> getAsyncAccountState(String accountId) {
        var balanceUiFetchedCriteriaDto = AccountBalanceFetchedCriteriaDto.of(accountId);
        var energyFetchedCriteriaDto = AccountEnergyFetchedCriteriaDto.of(accountId);
        var accountCardFetchedCriteriaDto = AccountCardUiFetchedCriteriaDto.of(accountId, Set.of(ACTIVE));
        return CompletableFutures.combine(
                        accountBalanceCommunicationService.fetchAsyncAllAccountBalanceForUi(balanceUiFetchedCriteriaDto, cachedMDCThreadPoolTaskExecutor)
                                .thenApply(PageDto::getElements),
                        accountEnergyCommunicationService.fetchAsyncAllAccountEnergyForUi(energyFetchedCriteriaDto, cachedMDCThreadPoolTaskExecutor)
                                .thenApply(PageDto::getElements),
                        inventoryCommunicationService.getAsyncAccountCardStateForUi(accountCardFetchedCriteriaDto, cachedMDCThreadPoolTaskExecutor),
                        AccountStateUiDto::new
                )
                .toCompletableFuture();
    }

}
