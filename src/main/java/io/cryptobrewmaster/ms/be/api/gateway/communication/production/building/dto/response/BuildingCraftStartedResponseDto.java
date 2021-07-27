package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingCraftStartedResponseDto {
    @NotNull
    private Long buildingCraftId;
    @NotNull
    private Long craftTime;
    @NotNull
    private Long craftEndedTime;
}
