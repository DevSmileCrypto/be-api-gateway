package io.cryptobrewmaster.ms.be.api.gateway.web.model.building.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.BuildingName;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingCraftChances;
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
public class BuildingCraftChancesUiDto {
    @NotBlank
    private String requestId;
    @NotNull
    private BuildingName buildingName;
    @NotNull
    private Map<CardQuality, Double> qualityChances;

    public static BuildingCraftChancesUiDto of(KafkaBuildingCraftChances kafkaBuildingCraftChances) {
        return new BuildingCraftChancesUiDto(
                kafkaBuildingCraftChances.getRequestId(), kafkaBuildingCraftChances.getBuildingName(),
                kafkaBuildingCraftChances.getQualityChances()
        );
    }
}
