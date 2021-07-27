package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.craft.HopsFieldBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.rent.HopsFieldBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.KafkaHopsFieldBuilding;
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
public class HopsFieldBuildingUiDto {
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
    private List<HopsFieldBuildingCraftUiDto> hopsFieldBuildingCrafts;
    @NotNull
    @ToString.Exclude
    private List<HopsFieldBuildingRentUiDto> hopsFieldBuildingRents;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static HopsFieldBuildingUiDto of(KafkaHopsFieldBuilding kafkaHopsFieldBuilding) {
        return new HopsFieldBuildingUiDto(
                kafkaHopsFieldBuilding.getId(), kafkaHopsFieldBuilding.getCondition(), kafkaHopsFieldBuilding.getMinCondition(),
                kafkaHopsFieldBuilding.getMaxCondition(), List.of(), List.of(),
                kafkaHopsFieldBuilding.getCreatedDate(), kafkaHopsFieldBuilding.getLastModifiedDate()
        );
    }
}
