package io.cryptobrewmaster.ms.be.api.gateway.service.state;

import com.spotify.futures.CompletableFutures;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.criteria.AccountBalanceFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.service.AccountBalanceCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.criteria.AccountEnergyFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.service.AccountEnergyCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardUiFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service.InventoryCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.service.ProductionBuildingCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.StateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.account.AccountStateUiDto;
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
        var accountState = getAccountState(authentication.getAccountId());
        var accountBuildingState = productionBuildingCommunicationService.getAccountBuildingStateForUi(
                authentication.getAccountId()
        );
        return new StateUiDto(accountState, accountBuildingState);
    }

    private AccountStateUiDto getAccountState(String accountId) {
        var accountBalancesCompletableFuture = CompletableFuture.supplyAsync(() -> {
            var balanceUiFetchedCriteriaDto = AccountBalanceFetchedCriteriaDto.of(accountId);
            return accountBalanceCommunicationService.fetchAllAccountBalanceForUi(balanceUiFetchedCriteriaDto).getElements();
        }, cachedMDCThreadPoolTaskExecutor);

        var accountEnergiesCompletableFuture = CompletableFuture.supplyAsync(() -> {
            var energyFetchedCriteriaDto = AccountEnergyFetchedCriteriaDto.of(accountId);
            return accountEnergyCommunicationService.fetchAllAccountEnergyForUi(energyFetchedCriteriaDto).getElements();
        }, cachedMDCThreadPoolTaskExecutor);

        var accountCardStateCompletableFuture = CompletableFuture.supplyAsync(() -> {
            var cardStatuses = Set.of(ACTIVE);

            var accountCardFetchedCriteriaDto = AccountCardUiFetchedCriteriaDto.of(accountId, cardStatuses);
            return inventoryCommunicationService.getAccountCardStateForUi(accountCardFetchedCriteriaDto);

        }, cachedMDCThreadPoolTaskExecutor);

        try {
            return CompletableFutures.combine(
                            accountBalancesCompletableFuture, accountEnergiesCompletableFuture, accountCardStateCompletableFuture,
                            AccountStateUiDto::new
                    )
                    .toCompletableFuture()
                    .get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new InnerServiceException(
                    String.format("Error by interrupted exception while on get account state by account id = %s. Error = %s",
                            accountId, e.getMessage())
            );
        } catch (Exception e) {
            throw new InnerServiceException(
                    String.format("Error while on get account state by account id = %s. Error = %s",
                            accountId, e.getMessage())
            );
        }
    }

}
