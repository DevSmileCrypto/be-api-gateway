package io.cryptobrewmaster.ms.be.api.gateway.web.model.building.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.BuildingName;
import io.cryptobrewmaster.ms.be.library.constants.building.BuildingStateType;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingStateUiDto {
    @NotBlank
    private String requestId;
    @NotNull
    private BuildingName buildingName;
    @NotNull
    private Long queueQuantity;
    @NotNull
    private Map<BuildingStateType, Double> efficiencyInPercent;
    @NotNull
    private Map<BuildingStateType, Double> restoredConditionCost;
    @NotNull
    private Map<BuildingStateType, Long> craftTime;

    public static BuildingStateUiDto of(KafkaBuildingState kafkaBuildingState) {
        return new BuildingStateUiDto(
                kafkaBuildingState.getRequestId(), kafkaBuildingState.getBuildingName(), kafkaBuildingState.getQueueQuantity(),
                kafkaBuildingState.getEfficiencyInPercent(), kafkaBuildingState.getRestoredConditionCost(), kafkaBuildingState.getCraftTime()
        );
    }
}
