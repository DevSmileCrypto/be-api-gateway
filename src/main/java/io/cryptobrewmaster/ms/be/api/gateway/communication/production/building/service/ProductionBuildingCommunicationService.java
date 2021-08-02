package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.AccountBuildingStateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.craft.history.AcademyYeastLabBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.craft.history.GrainFieldBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.craft.history.HopsFieldBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.craft.history.BrewHouseBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.craft.history.MaltHouseBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingCraftStartedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingRentedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.response.BuildingCraftStartedResponseDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.craft.history.WaterPumpBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * The interface Production building communication service.
 */
public interface ProductionBuildingCommunicationService {

    /**
     * Gets all water pump building craft history for ui.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all water pump building craft history for ui
     */
    PageDto<WaterPumpBuildingCraftHistoryUiDto> getAllWaterPumpBuildingCraftHistoryForUi(String accountId, Integer page, Integer size);

    /**
     * Rent water pump building.
     *
     * @param accountId                the account id
     * @param buildingRentedRequestDto the building rented request dto
     */
    void rentWaterPumpBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto);

    /**
     * Restore condition water pump building.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     */
    void restoreConditionWaterPumpBuilding(String accountId, Long buildingId);

    /**
     * Craft water pump building building craft started response dto.
     *
     * @param accountId                      the account id
     * @param buildingCraftStartedRequestDto the building craft started request dto
     * @return the building craft started response dto
     */
    BuildingCraftStartedResponseDto craftWaterPumpBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto);

    /**
     * Claim craft water pump building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void claimCraftWaterPumpBuilding(String accountId, Long buildingCraftId);

    /**
     * Completion craft water pump building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void completionCraftWaterPumpBuilding(String accountId, Long buildingCraftId);

    /**
     * Gets all grain field building craft history for ui.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all grain field building craft history for ui
     */
    PageDto<GrainFieldBuildingCraftHistoryUiDto> getAllGrainFieldBuildingCraftHistoryForUi(String accountId, Integer page, Integer size);

    /**
     * Rent grain field building.
     *
     * @param accountId                the account id
     * @param buildingRentedRequestDto the building rented request dto
     */
    void rentGrainFieldBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto);

    /**
     * Restore condition grain field building.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     */
    void restoreConditionGrainFieldBuilding(String accountId, Long buildingId);

    /**
     * Craft grain field building building craft started response dto.
     *
     * @param accountId                      the account id
     * @param buildingCraftStartedRequestDto the building craft started request dto
     * @return the building craft started response dto
     */
    BuildingCraftStartedResponseDto craftGrainFieldBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto);

    /**
     * Claim craft grain field building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void claimCraftGrainFieldBuilding(String accountId, Long buildingCraftId);

    /**
     * Completion craft grain field building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void completionCraftGrainFieldBuilding(String accountId, Long buildingCraftId);

    /**
     * Gets all hops field building craft history for ui.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all hops field building craft history for ui
     */
    PageDto<HopsFieldBuildingCraftHistoryUiDto> getAllHopsFieldBuildingCraftHistoryForUi(String accountId, Integer page, Integer size);

    /**
     * Rent hops field building.
     *
     * @param accountId                the account id
     * @param buildingRentedRequestDto the building rented request dto
     */
    void rentHopsFieldBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto);

    /**
     * Restore condition hops field building.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     */
    void restoreConditionHopsFieldBuilding(String accountId, Long buildingId);

    /**
     * Craft hops field building building craft started response dto.
     *
     * @param accountId                      the account id
     * @param buildingCraftStartedRequestDto the building craft started request dto
     * @return the building craft started response dto
     */
    BuildingCraftStartedResponseDto craftHopsFieldBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto);

    /**
     * Claim craft hops field building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void claimCraftHopsFieldBuilding(String accountId, Long buildingCraftId);

    /**
     * Completion craft hops field building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void completionCraftHopsFieldBuilding(String accountId, Long buildingCraftId);

    /**
     * Gets all academy yeast lab building craft history for ui.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all academy yeast lab building craft history for ui
     */
    PageDto<AcademyYeastLabBuildingCraftHistoryUiDto> getAllAcademyYeastLabBuildingCraftHistoryForUi(String accountId, Integer page, Integer size);

    /**
     * Rent academy yeast lab building.
     *
     * @param accountId                the account id
     * @param buildingRentedRequestDto the building rented request dto
     */
    void rentAcademyYeastLabBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto);

    /**
     * Restore condition academy yeast lab building.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     */
    void restoreConditionAcademyYeastLabBuilding(String accountId, Long buildingId);

    /**
     * Craft academy yeast lab building building craft started response dto.
     *
     * @param accountId                      the account id
     * @param buildingCraftStartedRequestDto the building craft started request dto
     * @return the building craft started response dto
     */
    BuildingCraftStartedResponseDto craftAcademyYeastLabBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto);

    /**
     * Claim craft academy yeast lab building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void claimCraftAcademyYeastLabBuilding(String accountId, Long buildingCraftId);

    /**
     * Completion craft academy yeast lab building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void completionCraftAcademyYeastLabBuilding(String accountId, Long buildingCraftId);

    /**
     * Gets all malt house building craft history for ui.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all malt house building craft history for ui
     */
    PageDto<MaltHouseBuildingCraftHistoryUiDto> getAllMaltHouseBuildingCraftHistoryForUi(String accountId, Integer page, Integer size);

    /**
     * Rent malt house building.
     *
     * @param accountId                the account id
     * @param buildingRentedRequestDto the building rented request dto
     */
    void rentMaltHouseBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto);

    /**
     * Restore condition malt house building.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     */
    void restoreConditionMaltHouseBuilding(String accountId, Long buildingId);

    /**
     * Craft malt house building building craft started response dto.
     *
     * @param accountId                      the account id
     * @param buildingCraftStartedRequestDto the building craft started request dto
     * @return the building craft started response dto
     */
    BuildingCraftStartedResponseDto craftMaltHouseBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto);

    /**
     * Claim craft malt house building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void claimCraftMaltHouseBuilding(String accountId, Long buildingCraftId);

    /**
     * Completion craft malt house building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void completionCraftMaltHouseBuilding(String accountId, Long buildingCraftId);

    /**
     * Gets all brew house building craft history for ui.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all brew house building craft history for ui
     */
    PageDto<BrewHouseBuildingCraftHistoryUiDto> getAllBrewHouseBuildingCraftHistoryForUi(String accountId, Integer page, Integer size);

    /**
     * Restore condition brew house building.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     */
    void restoreConditionBrewHouseBuilding(String accountId, Long buildingId);

    /**
     * Craft brew house building building craft started response dto.
     *
     * @param accountId                      the account id
     * @param buildingCraftStartedRequestDto the building craft started request dto
     * @return the building craft started response dto
     */
    BuildingCraftStartedResponseDto craftBrewHouseBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto);

    /**
     * Claim craft brew house building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void claimCraftBrewHouseBuilding(String accountId, Long buildingCraftId);

    /**
     * Completion craft brew house building.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     */
    void completionCraftBrewHouseBuilding(String accountId, Long buildingCraftId);

    /**
     * Gets account building state for ui.
     *
     * @param accountId the account id
     * @return the account building state for ui
     */
    AccountBuildingStateUiDto getAccountBuildingStateForUi(String accountId);

    /**
     * Gets async account building state for ui.
     *
     * @param accountId the account id
     * @param executor  the executor
     * @return the async account building state for ui
     */
    default CompletableFuture<AccountBuildingStateUiDto> getAsyncAccountBuildingStateForUi(String accountId,
                                                                                           Executor executor) {
        return CompletableFuture.supplyAsync(() -> getAccountBuildingStateForUi(accountId), executor);
    }

}
