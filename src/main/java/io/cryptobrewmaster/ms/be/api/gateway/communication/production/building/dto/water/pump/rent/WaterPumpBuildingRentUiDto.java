package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.RentStatus;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.rent.KafkaWaterPumpBuildingRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WaterPumpBuildingRentUiDto {
    @NotNull
    private Long id;
    @NotNull
    private RentStatus status;
    @NotNull
    private Long rentTime;
    @NotNull
    private Long rentEndedDate;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static WaterPumpBuildingRentUiDto of(KafkaWaterPumpBuildingRent kafkaWaterPumpBuildingRent) {
        return new WaterPumpBuildingRentUiDto(
                kafkaWaterPumpBuildingRent.getId(), kafkaWaterPumpBuildingRent.getStatus(),
                kafkaWaterPumpBuildingRent.getRentTime(), kafkaWaterPumpBuildingRent.getRentEndedDate(),
                kafkaWaterPumpBuildingRent.getCreatedDate(), kafkaWaterPumpBuildingRent.getLastModifiedDate()
        );
    }
}
