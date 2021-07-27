package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildingCraftStartedRequestDto {
    @NotNull
    private Long buildingId;
    @NotBlank
    private String recipeId;
    @NotNull
    private Set<Long> accountResourceCardIds;
}
