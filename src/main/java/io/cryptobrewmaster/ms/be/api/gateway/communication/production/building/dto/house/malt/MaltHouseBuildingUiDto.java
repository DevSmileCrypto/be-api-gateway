package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.craft.MaltHouseBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.rent.MaltHouseBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.KafkaMaltHouseBuilding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaltHouseBuildingUiDto {
    @NotNull
    private Long id;
    @NotNull
    private Long condition;
    @NotNull
    private Long minCondition;
    @NotNull
    private Long maxCondition;
    @NotNull
    @ToString.Exclude
    private List<MaltHouseBuildingCraftUiDto> maltHouseBuildingCrafts;
    @NotNull
    @ToString.Exclude
    private List<MaltHouseBuildingRentUiDto> maltHouseBuildingRents;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static MaltHouseBuildingUiDto of(KafkaMaltHouseBuilding kafkaMaltHouseBuilding) {
        return new MaltHouseBuildingUiDto(
                kafkaMaltHouseBuilding.getId(), kafkaMaltHouseBuilding.getCondition(), kafkaMaltHouseBuilding.getMinCondition(),
                kafkaMaltHouseBuilding.getMaxCondition(), List.of(), List.of(),
                kafkaMaltHouseBuilding.getCreatedDate(), kafkaMaltHouseBuilding.getLastModifiedDate()
        );
    }
}
