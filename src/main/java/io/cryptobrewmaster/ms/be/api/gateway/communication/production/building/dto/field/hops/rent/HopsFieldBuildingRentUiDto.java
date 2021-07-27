package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.RentStatus;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.rent.KafkaHopsFieldBuildingRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HopsFieldBuildingRentUiDto {
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

    public static HopsFieldBuildingRentUiDto of(KafkaHopsFieldBuildingRent kafkaHopsFieldBuildingRent) {
        return new HopsFieldBuildingRentUiDto(
                kafkaHopsFieldBuildingRent.getId(), kafkaHopsFieldBuildingRent.getStatus(),
                kafkaHopsFieldBuildingRent.getRentTime(), kafkaHopsFieldBuildingRent.getRentEndedDate(),
                kafkaHopsFieldBuildingRent.getCreatedDate(), kafkaHopsFieldBuildingRent.getLastModifiedDate()
        );
    }
}
