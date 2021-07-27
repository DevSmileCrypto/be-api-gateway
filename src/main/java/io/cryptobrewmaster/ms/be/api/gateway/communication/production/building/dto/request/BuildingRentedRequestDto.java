package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingRentedRequestDto {
    @NotNull
    private Long buildingId;
    @NotNull
    private Long buildingRentTariffId;
}
