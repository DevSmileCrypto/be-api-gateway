package io.cryptobrewmaster.ms.be.api.gateway.web.controller.building;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingCraftStartedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingRentedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.response.BuildingCraftStartedResponseDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.craft.history.WaterPumpBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.service.ProductionBuildingCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/building/water-pump")
@RestController
public class WaterPumpBuildingController {

    private final ProductionBuildingCommunicationService productionBuildingCommunicationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/craft/history/ui")
    public PageDto<WaterPumpBuildingCraftHistoryUiDto> getAllCraftHistory(@RequestParam(required = false) Integer page,
                                                                          @RequestParam(required = false) Integer size) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to get all water pump building craft history for ui received: Account id = {}", accountId);
        var waterPumpBuildingCraftHistoryUiDtoPageDto = productionBuildingCommunicationService.getAllWaterPumpBuildingCraftHistoryForUi(accountId, page, size);
        log.info("Response on get all water pump building craft history for ui: Account id = {}, {}", accountId, waterPumpBuildingCraftHistoryUiDtoPageDto);
        return waterPumpBuildingCraftHistoryUiDtoPageDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/rent")
    public void rent(@Valid @NotNull @RequestBody BuildingRentedRequestDto buildingRentedRequestDto) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to rent water pump building: {}", buildingRentedRequestDto);
        productionBuildingCommunicationService.rentWaterPumpBuilding(accountId, buildingRentedRequestDto);
        log.info("Response on rent water pump building: {}", buildingRentedRequestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{buildingId}/restore/condition")
    public void restoreCondition(@Valid @NotNull @PathVariable Long buildingId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to restore condition in water pump building: Building id = {}, account id = {}", buildingId, accountId);
        productionBuildingCommunicationService.restoreConditionWaterPumpBuilding(accountId, buildingId);
        log.info("Response on restore condition in water pump building: Building id = {}, account id = {}", buildingId, accountId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/craft")
    public BuildingCraftStartedResponseDto craft(@Valid @NotNull @RequestBody BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to start craft in water pump building: {}", buildingCraftStartedRequestDto);
        var buildingCraftStartedResponseDto = productionBuildingCommunicationService.craftWaterPumpBuilding(accountId, buildingCraftStartedRequestDto);
        log.info("Response on start craft in water pump building: {}", buildingCraftStartedResponseDto);
        return buildingCraftStartedResponseDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/craft/{buildingCraftId}/claim")
    public void claimCraft(@Valid @NotNull @PathVariable Long buildingCraftId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to claim craft in water pump building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
        productionBuildingCommunicationService.claimCraftWaterPumpBuilding(accountId, buildingCraftId);
        log.info("Response on claim craft in water pump building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/craft/{buildingCraftId}/completion")
    public void completionCraft(@Valid @NotNull @PathVariable Long buildingCraftId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to completion craft in water pump building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
        productionBuildingCommunicationService.completionCraftWaterPumpBuilding(accountId, buildingCraftId);
        log.info("Response on completion craft in water pump building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
    }

}
