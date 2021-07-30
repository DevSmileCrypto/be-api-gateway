package io.cryptobrewmaster.ms.be.api.gateway.web.controller.rest.building;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.craft.history.AcademyYeastLabBuildingCraftHistoryUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingCraftStartedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request.BuildingRentedRequestDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.response.BuildingCraftStartedResponseDto;
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
@RequestMapping("/api/building/academy/lab/yeast")
@RestController
public class AcademyYeastLabBuildingController {

    private final ProductionBuildingCommunicationService productionBuildingCommunicationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/craft/history")
    public PageDto<AcademyYeastLabBuildingCraftHistoryUiDto> getAllCraftHistory(@RequestParam(required = false) Integer page,
                                                                                @RequestParam(required = false) Integer size) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to get all academy yeast lab building craft history for ui received: Account id = {}", accountId);
        var academyYeastLabBuildingCraftHistoryUiDtoPageDto = productionBuildingCommunicationService.getAllAcademyYeastLabBuildingCraftHistoryForUi(accountId, page, size);
        log.info("Response on get all academy yeast lab building craft history for ui: Account id = {}, {}", accountId, academyYeastLabBuildingCraftHistoryUiDtoPageDto);
        return academyYeastLabBuildingCraftHistoryUiDtoPageDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/rent")
    public void rent(@Valid @NotNull @RequestBody BuildingRentedRequestDto buildingRentedRequestDto) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to rent academy yeast lab building: {}", buildingRentedRequestDto);
        productionBuildingCommunicationService.rentAcademyYeastLabBuilding(accountId, buildingRentedRequestDto);
        log.info("Response on rent academy yeast lab building: {}", buildingRentedRequestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{buildingId}/restore/condition")
    public void restoreCondition(@Valid @NotNull @PathVariable Long buildingId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to restore condition in academy yeast lab building: Building id = {}, account id = {}", buildingId, accountId);
        productionBuildingCommunicationService.restoreConditionAcademyYeastLabBuilding(accountId, buildingId);
        log.info("Response on restore condition in academy yeast lab building: Building id = {}, account id = {}", buildingId, accountId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/craft")
    public BuildingCraftStartedResponseDto craft(@Valid @NotNull @RequestBody BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to start craft in academy yeast lab building: {}", buildingCraftStartedRequestDto);
        var buildingCraftStartedResponseDto = productionBuildingCommunicationService.craftAcademyYeastLabBuilding(accountId, buildingCraftStartedRequestDto);
        log.info("Response on start craft in academy yeast lab building: {}", buildingCraftStartedResponseDto);
        return buildingCraftStartedResponseDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/craft/{buildingCraftId}/claim")
    public void claimCraft(@Valid @NotNull @PathVariable Long buildingCraftId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to claim craft in academy yeast lab building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
        productionBuildingCommunicationService.claimCraftAcademyYeastLabBuilding(accountId, buildingCraftId);
        log.info("Response on claim craft in academy yeast lab building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/craft/{buildingCraftId}/completion")
    public void completionCraft(@Valid @NotNull @PathVariable Long buildingCraftId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to completion craft in academy yeast lab building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
        productionBuildingCommunicationService.completionCraftAcademyYeastLabBuilding(accountId, buildingCraftId);
        log.info("Response on completion craft in academy yeast lab building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
    }

}
