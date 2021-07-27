package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.RentStatus;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.rent.KafkaGrainFieldBuildingRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrainFieldBuildingRentUiDto {
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

    public static GrainFieldBuildingRentUiDto of(KafkaGrainFieldBuildingRent kafkaGrainFieldBuildingRent) {
        return new GrainFieldBuildingRentUiDto(
                kafkaGrainFieldBuildingRent.getId(), kafkaGrainFieldBuildingRent.getStatus(),
                kafkaGrainFieldBuildingRent.getRentTime(), kafkaGrainFieldBuildingRent.getRentEndedDate(),
                kafkaGrainFieldBuildingRent.getCreatedDate(), kafkaGrainFieldBuildingRent.getLastModifiedDate()
        );
    }
}
