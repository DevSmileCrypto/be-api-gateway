package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.craft.WaterPumpBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.rent.WaterPumpBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.KafkaWaterPumpBuilding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WaterPumpBuildingUiDto {
    @NotNull
    private Long id;
    @NotNull
    private Long condition;
    @NotNull
    private Long minCondition;
    @NotNull
    private Long maxCondition;
    @NotNull
    @ToString.Exclude
    private List<WaterPumpBuildingCraftUiDto> waterPumpBuildingCrafts;
    @NotNull
    @ToString.Exclude
    private List<WaterPumpBuildingRentUiDto> waterPumpBuildingRents;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static WaterPumpBuildingUiDto of(KafkaWaterPumpBuilding kafkaWaterPumpBuilding) {
        return new WaterPumpBuildingUiDto(
                kafkaWaterPumpBuilding.getId(), kafkaWaterPumpBuilding.getCondition(), kafkaWaterPumpBuilding.getMinCondition(),
                kafkaWaterPumpBuilding.getMaxCondition(), List.of(), List.of(),
                kafkaWaterPumpBuilding.getCreatedDate(), kafkaWaterPumpBuilding.getLastModifiedDate()
        );
    }
}
