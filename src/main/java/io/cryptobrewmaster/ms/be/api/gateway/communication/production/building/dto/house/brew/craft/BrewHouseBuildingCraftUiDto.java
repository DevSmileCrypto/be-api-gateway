package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.craft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.CraftStatus;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.beer.BeerCardName;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.brew.craft.KafkaBrewHouseBuildingCraft;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrewHouseBuildingCraftUiDto {
    @NotNull
    private Long id;
    @NotBlank
    private String beerRecipeId;
    @NotNull
    private CraftStatus status;

    private Long craftedAccountResourceCardId;
    @NotNull
    private BeerCardName craftedBeerCardName;
    @NotNull
    private CardQuality craftedCardQuality;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static BrewHouseBuildingCraftUiDto of(KafkaBrewHouseBuildingCraft kafkaBrewHouseBuildingCraft) {
        return new BrewHouseBuildingCraftUiDto(
                kafkaBrewHouseBuildingCraft.getId(), kafkaBrewHouseBuildingCraft.getBeerRecipeId(),
                kafkaBrewHouseBuildingCraft.getStatus(), kafkaBrewHouseBuildingCraft.getCraftedAccountBeerCardId(),
                kafkaBrewHouseBuildingCraft.getCraftedBeerCardName(), kafkaBrewHouseBuildingCraft.getCraftedCardQuality(),
                kafkaBrewHouseBuildingCraft.getCreatedDate(), kafkaBrewHouseBuildingCraft.getLastModifiedDate()
        );
    }
}
