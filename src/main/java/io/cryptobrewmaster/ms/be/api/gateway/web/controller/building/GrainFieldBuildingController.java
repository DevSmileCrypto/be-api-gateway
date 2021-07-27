package io.cryptobrewmaster.ms.be.api.gateway.web.controller.building;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.craft.history.GrainFieldBuildingCraftHistoryUiDto;
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
@RequestMapping("/api/building/field/grain")
@RestController
public class GrainFieldBuildingController {

    private final ProductionBuildingCommunicationService productionBuildingCommunicationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/craft/history/ui")
    public PageDto<GrainFieldBuildingCraftHistoryUiDto> getAllCraftHistory(@RequestParam(required = false) Integer page,
                                                                           @RequestParam(required = false) Integer size) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to get all grain field building craft history for ui received: Account id = {}", accountId);
        var grainFieldBuildingCraftHistoryUiDtoPageDto = productionBuildingCommunicationService.getAllGrainFieldBuildingCraftHistoryForUi(accountId, page, size);
        log.info("Response on get all grain field building craft history for ui: Account id = {}, {}", accountId, grainFieldBuildingCraftHistoryUiDtoPageDto);
        return grainFieldBuildingCraftHistoryUiDtoPageDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/rent")
    public void rent(@Valid @NotNull @RequestBody BuildingRentedRequestDto buildingRentedRequestDto) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to rent grain field building: {}", buildingRentedRequestDto);
        productionBuildingCommunicationService.rentGrainFieldBuilding(accountId, buildingRentedRequestDto);
        log.info("Response on rent grain field building: {}", buildingRentedRequestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{buildingId}/restore/condition")
    public void restoreCondition(@Valid @NotNull @PathVariable Long buildingId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to restore condition in grain field building: Building id = {}, account id = {}", buildingId, accountId);
        productionBuildingCommunicationService.restoreConditionGrainFieldBuilding(accountId, buildingId);
        log.info("Response on restore condition in grain field building: Building id = {}, account id = {}", buildingId, accountId);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/craft")
    public BuildingCraftStartedResponseDto craft(@Valid @NotNull @RequestBody BuildingCraftStartedRequestDto buildingCraftStartedRequestDto) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to start craft in grain field building: {}", buildingCraftStartedRequestDto);
        var buildingCraftStartedResponseDto = productionBuildingCommunicationService.craftGrainFieldBuilding(accountId, buildingCraftStartedRequestDto);
        log.info("Response on start craft in grain field building: {}", buildingCraftStartedResponseDto);
        return buildingCraftStartedResponseDto;
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/craft/{buildingCraftId}/claim")
    public void claimCraft(@Valid @NotNull @PathVariable Long buildingCraftId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to claim craft in grain field building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
        productionBuildingCommunicationService.claimCraftGrainFieldBuilding(accountId, buildingCraftId);
        log.info("Response on claim craft in grain field building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/craft/{buildingCraftId}/completion")
    public void completionCraft(@Valid @NotNull @PathVariable Long buildingCraftId) {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to completion craft in grain field building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
        productionBuildingCommunicationService.completionCraftGrainFieldBuilding(accountId, buildingCraftId);
        log.info("Response on completion craft in grain field building: Building craft id = {}, account id = {}", buildingCraftId, accountId);
    }

}
