package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.craft.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.beer.BeerCardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrewHouseBuildingCraftHistoryUiDto {
    @NotNull
    private Long id;
    @NotNull
    private BeerCardName craftedBeerCardName;
    @NotNull
    private CardQuality craftedCardQuality;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;
}
