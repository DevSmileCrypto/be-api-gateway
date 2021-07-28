package io.cryptobrewmaster.ms.be.api.gateway.web.model.building.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.BuildingName;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingStateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingStateRequestUiDto {
    @NotBlank
    private String requestId;
    @NotNull
    private BuildingName buildingName;
    @NotNull
    private Long buildingId;
    private String recipeId;

    public KafkaBuildingStateRequest generateKafkaDto(String accountId) {
        return new KafkaBuildingStateRequest(
                getRequestId(), accountId, getBuildingName(), getBuildingId(), getRecipeId()
        );
    }
}
