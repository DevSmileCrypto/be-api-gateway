package io.cryptobrewmaster.ms.be.api.gateway.service.state;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.criteria.AccountBalanceFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.service.AccountBalanceCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.criteria.AccountEnergyFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.service.AccountEnergyCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.criteria.AccountBeerCardFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.criteria.AccountResourceCardFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service.InventoryCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.service.ProductionBuildingCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.StateDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.account.AccountStateDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.building.BuildingStateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static io.cryptobrewmaster.ms.be.library.constants.card.CardStatus.ACTIVE;
import static io.cryptobrewmaster.ms.be.library.constants.card.CardStatus.CRAFTED;

@RequiredArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final AccountBalanceCommunicationService accountBalanceCommunicationService;
    private final AccountEnergyCommunicationService accountEnergyCommunicationService;
    private final InventoryCommunicationService inventoryCommunicationService;

    private final ProductionBuildingCommunicationService productionBuildingCommunicationService;

    @Override
    public StateDto getState(AccountAuthentication authentication) {
        var accountState = getAccountState(authentication.getAccountId());
        var buildingState = getBuildingState(authentication.getAccountId());
        return new StateDto(accountState, buildingState);
    }

    private AccountStateDto getAccountState(String accountId) {
        var balanceUiFetchedCriteriaDto = AccountBalanceFetchedCriteriaDto.of(accountId);
        var accountBalanceForUi = accountBalanceCommunicationService.fetchAllAccountBalanceForUi(balanceUiFetchedCriteriaDto);

        var energyFetchedCriteriaDto = AccountEnergyFetchedCriteriaDto.of(accountId);
        var accountEnergyForUi = accountEnergyCommunicationService.fetchAllAccountEnergyForUi(energyFetchedCriteriaDto);

        var cardStatuses = Set.of(ACTIVE, CRAFTED);

        var resourceCardFetchedCriteriaDto = AccountResourceCardFetchedCriteriaDto.of(accountId, cardStatuses);
        var accountResourceCardForUi = inventoryCommunicationService.fetchAllAccountResourceCardForUi(resourceCardFetchedCriteriaDto);

        var beerCardFetchedCriteriaDto = AccountBeerCardFetchedCriteriaDto.of(accountId, cardStatuses);
        var accountBeerCardForUi = inventoryCommunicationService.fetchAllAccountBeerCardForUi(beerCardFetchedCriteriaDto);

        return new AccountStateDto(
                accountBalanceForUi.getElements(), accountEnergyForUi.getElements(),
                accountResourceCardForUi.getElements(), accountBeerCardForUi.getElements()
        );
    }

    private BuildingStateDto getBuildingState(String accountId) {
        var waterPumpBuildingUiDto = productionBuildingCommunicationService.getWaterPumpBuildingForUi(accountId);
        var grainFieldBuildingUiDto = productionBuildingCommunicationService.getGrainFieldBuildingForUi(accountId);
        var hopsFieldBuildingUiDto = productionBuildingCommunicationService.getHopsFieldBuildingForUi(accountId);
        var academyYeastLabBuildingUiDto = productionBuildingCommunicationService.getAcademyYeastLabBuildingForUi(accountId);
        var maltHouseBuildingUiDto = productionBuildingCommunicationService.getMaltHouseBuildingForUi(accountId);
        var brewHouseBuildingUiDto = productionBuildingCommunicationService.getBrewHouseBuildingForUi(accountId);
        return new BuildingStateDto(
                waterPumpBuildingUiDto, grainFieldBuildingUiDto, hopsFieldBuildingUiDto,
                academyYeastLabBuildingUiDto, maltHouseBuildingUiDto, brewHouseBuildingUiDto
        );
    }

}
