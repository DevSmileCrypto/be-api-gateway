package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.AcademyYeastLabBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.craft.history.AcademyYeastLabBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.GrainFieldBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.craft.history.GrainFieldBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.HopsFieldBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.craft.history.HopsFieldBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.BrewHouseBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.craft.history.BrewHouseBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.MaltHouseBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.craft.history.MaltHouseBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingCraftStartedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingRentedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.response.BuildingCraftStartedResponseDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.WaterPumpBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.craft.history.WaterPumpBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.uri.ProductionBuildingUriService;
import io.cryptobrewmaster.ms.be.library.communication.BaseCommunicationService;
import io.cryptobrewmaster.ms.be.library.communication.model.RequestLog;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class ProductionBuildingCommunicationServiceImpl extends BaseCommunicationService implements ProductionBuildingCommunicationService {

    private final ProductionBuildingUriService productionBuildingUriService;

    public ProductionBuildingCommunicationServiceImpl(RestTemplate productionBuildingRestTemplate,
                                                      ProductionBuildingUriService productionBuildingUriService) {
        super(productionBuildingRestTemplate);
        this.productionBuildingUriService = productionBuildingUriService;
    }

    String getMicroServiceName() {
        return MicroServiceName.BE_PRODUCTION_BUILDING.getProviderName();
    }

    @Override
    public WaterPumpBuildingUiDto getWaterPumpBuildingForUi(String accountId) {
        return performRequestWithResponse(
                productionBuildingUriService.getWaterPumpBuildingForUiByAccountIdUri(accountId),
                HttpMethod.GET, WaterPumpBuildingUiDto.class,
                new RequestLog(
                        "Request to get water pump building for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get water pump building for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get water pump building for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public PageDto<WaterPumpBuildingCraftHistoryUiDto> getAllWaterPumpBuildingCraftHistoryForUi(String accountId, Integer page, Integer size) {
        return performRequestWithResponse(
                productionBuildingUriService.getAllWaterPumpBuildingCraftHistoryForUiByAccountIdUri(accountId, page, size),
                HttpMethod.GET, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to get all water pump building craft history for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get all water pump building craft history for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get all water pump building craft history for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public void rentWaterPumpBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto) {
        performRequestWithoutResponse(
                productionBuildingUriService.getWaterPumpBuildingRentUri(accountId),
                HttpMethod.POST, buildingRentedRequestDto,
                new RequestLog(
                        "Request to rent water pump building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto),
                        "Response on rent water pump building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on rent water pump building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto)
                )
        );
    }

    @Override
    public void restoreConditionWaterPumpBuilding(String accountId, Long buildingId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getWaterPumpBuildingRestoreConditionUri(accountId, buildingId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to restore condition water pump building send to %s ms. Account id = %s, building id = %s",
                        List.of(getMicroServiceName(), accountId, buildingId),
                        "Response on restore condition water pump building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on restore condition water pump building request. Account id = %s, building id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingId)
                )
        );
    }

    @Override
    public BuildingCraftStartedResponseDto craftWaterPumpBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        return performRequestWithResponse(
                productionBuildingUriService.getWaterPumpBuildingCraftUri(accountId),
                HttpMethod.POST, buildingCraftStartedRequestDto, BuildingCraftStartedResponseDto.class,
                new RequestLog(
                        "Request to craft in water pump building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto),
                        "Response on craft in water pump building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on craft in water pump building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto)
                )
        );
    }

    @Override
    public void claimCraftWaterPumpBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getWaterPumpBuildingCraftClaimUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to claim water pump building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on claim water pump building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on claim water pump building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public void completionCraftWaterPumpBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getWaterPumpBuildingCraftCompletionUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to completion water pump building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on completion water pump building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on completion water pump building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public GrainFieldBuildingUiDto getGrainFieldBuildingForUi(String accountId) {
        return performRequestWithResponse(
                productionBuildingUriService.getGrainFieldBuildingForUiByAccountIdUri(accountId),
                HttpMethod.GET, GrainFieldBuildingUiDto.class,
                new RequestLog(
                        "Request to get grain field building for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get grain field building for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get grain field building for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public PageDto<GrainFieldBuildingCraftHistoryUiDto> getAllGrainFieldBuildingCraftHistoryForUi(String accountId, Integer page, Integer size) {
        return performRequestWithResponse(
                productionBuildingUriService.getAllGrainFieldBuildingCraftHistoryForUiByAccountIdUri(accountId, page, size),
                HttpMethod.GET, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to get all grain field building craft history for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get all grain field building craft history for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get all grain field building craft history for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public void rentGrainFieldBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto) {
        performRequestWithoutResponse(
                productionBuildingUriService.getGrainFieldBuildingRentUri(accountId),
                HttpMethod.POST, buildingRentedRequestDto,
                new RequestLog(
                        "Request to rent grain field building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto),
                        "Response on rent grain field building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on rent grain field building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto)
                )
        );
    }

    @Override
    public void restoreConditionGrainFieldBuilding(String accountId, Long buildingId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getGrainFieldBuildingRestoreConditionUri(accountId, buildingId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to restore condition grain field building send to %s ms. Account id = %s, building id = %s",
                        List.of(getMicroServiceName(), accountId, buildingId),
                        "Response on restore condition grain field building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on restore condition grain field building request. Account id = %s, building id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingId)
                )
        );
    }

    @Override
    public BuildingCraftStartedResponseDto craftGrainFieldBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        return performRequestWithResponse(
                productionBuildingUriService.getGrainFieldBuildingCraftUri(accountId),
                HttpMethod.POST, buildingCraftStartedRequestDto, BuildingCraftStartedResponseDto.class,
                new RequestLog(
                        "Request to craft in grain field building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto),
                        "Response on craft in grain field building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on craft in grain field building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto)
                )
        );
    }

    @Override
    public void claimCraftGrainFieldBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getGrainFieldBuildingCraftClaimUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to claim grain field building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on claim grain field building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on claim grain field building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public void completionCraftGrainFieldBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getGrainFieldBuildingCraftCompletionUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to completion grain field building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on completion grain field building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on completion grain field building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public HopsFieldBuildingUiDto getHopsFieldBuildingForUi(String accountId) {
        return performRequestWithResponse(
                productionBuildingUriService.getHopsFieldBuildingForUiByAccountIdUri(accountId),
                HttpMethod.GET, HopsFieldBuildingUiDto.class,
                new RequestLog(
                        "Request to get hops field building for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get hops field building for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get hops field building for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public PageDto<HopsFieldBuildingCraftHistoryUiDto> getAllHopsFieldBuildingCraftHistoryForUi(String accountId, Integer page, Integer size) {
        return performRequestWithResponse(
                productionBuildingUriService.getAllHopsFieldBuildingCraftHistoryForUiByAccountIdUri(accountId, page, size),
                HttpMethod.GET, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to get all hops field building craft history for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get all hops field building craft history for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get all hops field building craft history for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public void rentHopsFieldBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto) {
        performRequestWithoutResponse(
                productionBuildingUriService.getHopsFieldBuildingRentUri(accountId),
                HttpMethod.POST, buildingRentedRequestDto,
                new RequestLog(
                        "Request to rent hops field building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto),
                        "Response on rent hops field building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on rent hops field building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto)
                )
        );
    }

    @Override
    public void restoreConditionHopsFieldBuilding(String accountId, Long buildingId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getHopsFieldBuildingRestoreConditionUri(accountId, buildingId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to restore condition hops field building send to %s ms. Account id = %s, building id = %s",
                        List.of(getMicroServiceName(), accountId, buildingId),
                        "Response on restore condition hops field building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on restore condition hops field building request. Account id = %s, building id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingId)
                )
        );
    }

    @Override
    public BuildingCraftStartedResponseDto craftHopsFieldBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        return performRequestWithResponse(
                productionBuildingUriService.getHopsFieldBuildingCraftUri(accountId),
                HttpMethod.POST, buildingCraftStartedRequestDto, BuildingCraftStartedResponseDto.class,
                new RequestLog(
                        "Request to craft in hops field building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto),
                        "Response on craft in hops field building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on craft in hops field building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto)
                )
        );
    }

    @Override
    public void claimCraftHopsFieldBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getHopsFieldBuildingCraftClaimUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to claim hops field building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on claim hops field building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on claim hops field building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public void completionCraftHopsFieldBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getHopsFieldBuildingCraftCompletionUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to completion hops field building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on completion hops field building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on completion hops field building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public AcademyYeastLabBuildingUiDto getAcademyYeastLabBuildingForUi(String accountId) {
        return performRequestWithResponse(
                productionBuildingUriService.getAcademyYeastLabBuildingForUiByAccountIdUri(accountId),
                HttpMethod.GET, AcademyYeastLabBuildingUiDto.class,
                new RequestLog(
                        "Request to get academy yeast lab building for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get academy yeast lab building for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get academy yeast lab building for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public PageDto<AcademyYeastLabBuildingCraftHistoryUiDto> getAllAcademyYeastLabBuildingCraftHistoryForUi(String accountId, Integer page, Integer size) {
        return performRequestWithResponse(
                productionBuildingUriService.getAllAcademyYeastLabBuildingCraftHistoryForUiByAccountIdUri(accountId, page, size),
                HttpMethod.GET, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to get all academy yeast lab building craft history for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get all academy yeast lab building craft history for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get all academy yeast lab building craft history for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public void rentAcademyYeastLabBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto) {
        performRequestWithoutResponse(
                productionBuildingUriService.getAcademyYeastLabBuildingRentUri(accountId),
                HttpMethod.POST, buildingRentedRequestDto,
                new RequestLog(
                        "Request to rent academy yeast lab building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto),
                        "Response on rent academy yeast lab building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on rent academy yeast lab building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto)
                )
        );
    }

    @Override
    public void restoreConditionAcademyYeastLabBuilding(String accountId, Long buildingId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getAcademyYeastLabBuildingRestoreConditionUri(accountId, buildingId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to restore condition academy yeast lab building send to %s ms. Account id = %s, building id = %s",
                        List.of(getMicroServiceName(), accountId, buildingId),
                        "Response on restore condition academy yeast lab building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on restore condition academy yeast lab building request. Account id = %s, building id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingId)
                )
        );
    }

    @Override
    public BuildingCraftStartedResponseDto craftAcademyYeastLabBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        return performRequestWithResponse(
                productionBuildingUriService.getAcademyYeastLabBuildingCraftUri(accountId),
                HttpMethod.POST, buildingCraftStartedRequestDto, BuildingCraftStartedResponseDto.class,
                new RequestLog(
                        "Request to craft in academy yeast lab building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto),
                        "Response on craft in academy yeast lab building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on craft in academy yeast lab building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto)
                )
        );
    }

    @Override
    public void claimCraftAcademyYeastLabBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getAcademyYeastLabBuildingCraftClaimUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to claim academy yeast lab building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on claim academy yeast lab building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on claim academy yeast lab building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public void completionCraftAcademyYeastLabBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getAcademyYeastLabBuildingCraftCompletionUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to completion academy yeast lab building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on completion academy yeast lab building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on completion academy yeast lab building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public MaltHouseBuildingUiDto getMaltHouseBuildingForUi(String accountId) {
        return performRequestWithResponse(
                productionBuildingUriService.getMaltHouseBuildingForUiByAccountIdUri(accountId),
                HttpMethod.GET, MaltHouseBuildingUiDto.class,
                new RequestLog(
                        "Request to get malt house building for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get malt house building for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get malt house building for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public PageDto<MaltHouseBuildingCraftHistoryUiDto> getAllMaltHouseBuildingCraftHistoryForUi(String accountId, Integer page, Integer size) {
        return performRequestWithResponse(
                productionBuildingUriService.getAllMaltHouseBuildingCraftHistoryForUiByAccountIdUri(accountId, page, size),
                HttpMethod.GET, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to get all malt house building craft history for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get all malt house building craft history for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get all malt house building craft history for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public void rentMaltHouseBuilding(String accountId, BuildingRentedRequestDto buildingRentedRequestDto) {
        performRequestWithoutResponse(
                productionBuildingUriService.getMaltHouseBuildingRentUri(accountId),
                HttpMethod.POST, buildingRentedRequestDto,
                new RequestLog(
                        "Request to rent malt house building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto),
                        "Response on rent malt house building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on rent malt house building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingRentedRequestDto)
                )
        );
    }

    @Override
    public void restoreConditionMaltHouseBuilding(String accountId, Long buildingId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getMaltHouseBuildingRestoreConditionUri(accountId, buildingId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to restore condition malt house building send to %s ms. Account id = %s, building id = %s",
                        List.of(getMicroServiceName(), accountId, buildingId),
                        "Response on restore condition malt house building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on restore condition malt house building request. Account id = %s, building id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingId)
                )
        );
    }

    @Override
    public BuildingCraftStartedResponseDto craftMaltHouseBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        return performRequestWithResponse(
                productionBuildingUriService.getMaltHouseBuildingCraftUri(accountId),
                HttpMethod.POST, buildingCraftStartedRequestDto, BuildingCraftStartedResponseDto.class,
                new RequestLog(
                        "Request to craft in malt house building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto),
                        "Response on craft in malt house building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on craft in malt house building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto)
                )
        );
    }

    @Override
    public void claimCraftMaltHouseBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getMaltHouseBuildingCraftClaimUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to claim malt house building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on claim malt house building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on claim malt house building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public void completionCraftMaltHouseBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getMaltHouseBuildingCraftCompletionUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to completion malt house building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on completion malt house building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on completion malt house building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public BrewHouseBuildingUiDto getBrewHouseBuildingForUi(String accountId) {
        return performRequestWithResponse(
                productionBuildingUriService.getBrewHouseBuildingForUiByAccountIdUri(accountId),
                HttpMethod.GET, BrewHouseBuildingUiDto.class,
                new RequestLog(
                        "Request to get brew house building for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get brew house building for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get brew house building for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public PageDto<BrewHouseBuildingCraftHistoryUiDto> getAllBrewHouseBuildingCraftHistoryForUi(String accountId, Integer page, Integer size) {
        return performRequestWithResponse(
                productionBuildingUriService.getAllBrewHouseBuildingCraftHistoryForUiByAccountIdUri(accountId, page, size),
                HttpMethod.GET, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to get all brew house building craft history for ui by account id send to %s ms. Account id = %s",
                        List.of(getMicroServiceName(), accountId),
                        "Response on get all brew house building craft history for ui by account id from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get all brew house building craft history for ui by account id request. Account id = %s.",
                        List.of(getMicroServiceName(), accountId)
                )
        );
    }

    @Override
    public void restoreConditionBrewHouseBuilding(String accountId, Long buildingId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getBrewHouseBuildingRestoreConditionUri(accountId, buildingId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to restore condition brew house building send to %s ms. Account id = %s, building id = %s",
                        List.of(getMicroServiceName(), accountId, buildingId),
                        "Response on restore condition brew house building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on restore condition brew house building request. Account id = %s, building id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingId)
                )
        );
    }

    @Override
    public BuildingCraftStartedResponseDto craftBrewHouseBuilding(String accountId, BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        return performRequestWithResponse(
                productionBuildingUriService.getBrewHouseBuildingCraftUri(accountId),
                HttpMethod.POST, buildingCraftStartedRequestDto, BuildingCraftStartedResponseDto.class,
                new RequestLog(
                        "Request to craft in brew house building send to %s ms. Account id = %s, %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto),
                        "Response on craft in brew house building from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on craft in brew house building request. Account id = %s, %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftStartedRequestDto)
                )
        );
    }

    @Override
    public void claimCraftBrewHouseBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getBrewHouseBuildingCraftClaimUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to claim brew house building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on claim brew house building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on claim brew house building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }

    @Override
    public void completionCraftBrewHouseBuilding(String accountId, Long buildingCraftId) {
        performRequestWithoutResponse(
                productionBuildingUriService.getBrewHouseBuildingCraftCompletionUri(accountId, buildingCraftId),
                HttpMethod.PUT,
                new RequestLog(
                        "Request to completion brew house building craft send to %s ms. Account id = %s, building craft id = %s",
                        List.of(getMicroServiceName(), accountId, buildingCraftId),
                        "Response on completion brew house building craft from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on completion brew house building craft request. Account id = %s, building craft id = %s.",
                        List.of(getMicroServiceName(), accountId, buildingCraftId)
                )
        );
    }
}
