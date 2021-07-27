package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.craft.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.resource.ResourceCardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HopsFieldBuildingCraftHistoryUiDto {
    @NotNull
    private Long id;
    @NotNull
    private ResourceCardName craftedResourceCardName;
    @NotNull
    private CardQuality craftedCardQuality;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;
}
