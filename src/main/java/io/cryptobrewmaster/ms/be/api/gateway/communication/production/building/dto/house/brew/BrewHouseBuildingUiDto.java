package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.craft.BrewHouseBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.brew.KafkaBrewHouseBuilding;
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
public class BrewHouseBuildingUiDto {
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
    private List<BrewHouseBuildingCraftUiDto> brewHouseBuildingCrafts;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static BrewHouseBuildingUiDto of(KafkaBrewHouseBuilding kafkaBrewHouseBuilding) {
        return new BrewHouseBuildingUiDto(
                kafkaBrewHouseBuilding.getId(), kafkaBrewHouseBuilding.getCondition(), kafkaBrewHouseBuilding.getMinCondition(),
                kafkaBrewHouseBuilding.getMaxCondition(), List.of(),
                kafkaBrewHouseBuilding.getCreatedDate(), kafkaBrewHouseBuilding.getLastModifiedDate()
        );
    }
}
