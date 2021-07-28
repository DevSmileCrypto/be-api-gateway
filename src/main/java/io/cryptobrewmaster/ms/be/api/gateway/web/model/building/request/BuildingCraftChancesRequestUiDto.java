package io.cryptobrewmaster.ms.be.api.gateway.web.model.building.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.BuildingName;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingCraftChancesRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingCraftChancesRequestUiDto {
    @NotBlank
    private String requestId;
    @NotNull
    private BuildingName buildingName;
    @NotNull
    private Long buildingId;
    @NotNull
    private List<CardQuality> qualities;

    public KafkaBuildingCraftChancesRequest generateKafkaDto(String accountId) {
        return new KafkaBuildingCraftChancesRequest(
                getRequestId(), accountId, getBuildingName(), getBuildingId(), getQualities()
        );
    }
}
