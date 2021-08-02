package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.AcademyYeastLabBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.GrainFieldBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.HopsFieldBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.BrewHouseBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.MaltHouseBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.WaterPumpBuildingUiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBuildingStateUiDto {
    @NotNull
    private WaterPumpBuildingUiDto waterPumpBuilding;
    @NotNull
    private GrainFieldBuildingUiDto grainFieldBuilding;
    @NotNull
    private HopsFieldBuildingUiDto hopsFieldBuilding;
    @NotNull
    private AcademyYeastLabBuildingUiDto academyYeastLabBuilding;
    @NotNull
    private MaltHouseBuildingUiDto maltHouseBuilding;
    @NotNull
    private BrewHouseBuildingUiDto brewHouseBuilding;
}
