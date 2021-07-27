package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.RentStatus;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.rent.KafkaMaltHouseBuildingRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaltHouseBuildingRentUiDto {
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

    public static MaltHouseBuildingRentUiDto of(KafkaMaltHouseBuildingRent kafkaMaltHouseBuildingRent) {
        return new MaltHouseBuildingRentUiDto(
                kafkaMaltHouseBuildingRent.getId(), kafkaMaltHouseBuildingRent.getStatus(),
                kafkaMaltHouseBuildingRent.getRentTime(), kafkaMaltHouseBuildingRent.getRentEndedDate(),
                kafkaMaltHouseBuildingRent.getCreatedDate(), kafkaMaltHouseBuildingRent.getLastModifiedDate()
        );
    }
}
