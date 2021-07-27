package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.craft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.CraftStatus;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.resource.ResourceCardName;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.craft.KafkaHopsFieldBuildingCraft;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HopsFieldBuildingCraftUiDto {
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
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static HopsFieldBuildingCraftUiDto of(KafkaHopsFieldBuildingCraft kafkaHopsFieldBuildingCraft) {
        return new HopsFieldBuildingCraftUiDto(
                kafkaHopsFieldBuildingCraft.getId(), kafkaHopsFieldBuildingCraft.getResourceRecipeId(),
                kafkaHopsFieldBuildingCraft.getStatus(), kafkaHopsFieldBuildingCraft.getCraftedAccountResourceCardId(),
                kafkaHopsFieldBuildingCraft.getCraftedResourceCardName(), kafkaHopsFieldBuildingCraft.getCraftedCardQuality(),
                kafkaHopsFieldBuildingCraft.getCreatedDate(), kafkaHopsFieldBuildingCraft.getLastModifiedDate()
        );
    }
}
