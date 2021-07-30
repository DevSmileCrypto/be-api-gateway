package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.craft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.CraftStatus;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.resource.ResourceCardName;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.craft.KafkaMaltHouseBuildingCraft;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaltHouseBuildingCraftUiDto {
    @NotNull
    private Long id;
    @NotBlank
    private String resourceRecipeId;
    @NotNull
    private CraftStatus status;

    private Long craftedAccountResourceCardId;
    @NotNull
    private ResourceCardName craftedResourceCardName;
    @NotNull
    private CardQuality craftedCardQuality;
    @NotNull
    private Long craftTime;
    @NotNull
    private Long craftEndedDate;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static MaltHouseBuildingCraftUiDto of(KafkaMaltHouseBuildingCraft kafkaMaltHouseBuildingCraft) {
        return new MaltHouseBuildingCraftUiDto(
                kafkaMaltHouseBuildingCraft.getId(), kafkaMaltHouseBuildingCraft.getResourceRecipeId(),
                kafkaMaltHouseBuildingCraft.getStatus(), kafkaMaltHouseBuildingCraft.getCraftedAccountResourceCardId(),
                kafkaMaltHouseBuildingCraft.getCraftedResourceCardName(), kafkaMaltHouseBuildingCraft.getCraftedCardQuality(),
                kafkaMaltHouseBuildingCraft.getCraftTime(), kafkaMaltHouseBuildingCraft.getCraftEndedDate(),
                kafkaMaltHouseBuildingCraft.getCreatedDate(), kafkaMaltHouseBuildingCraft.getLastModifiedDate()
        );
    }
}
