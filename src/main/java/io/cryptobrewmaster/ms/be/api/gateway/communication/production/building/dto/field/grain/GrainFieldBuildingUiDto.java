package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.craft.GrainFieldBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.rent.GrainFieldBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.KafkaGrainFieldBuilding;
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
public class GrainFieldBuildingUiDto {
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
    private List<GrainFieldBuildingCraftUiDto> grainFieldBuildingCrafts;
    @NotNull
    @ToString.Exclude
    private List<GrainFieldBuildingRentUiDto> grainFieldBuildingRents;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static GrainFieldBuildingUiDto of(KafkaGrainFieldBuilding kafkaGrainFieldBuilding) {
        return new GrainFieldBuildingUiDto(
                kafkaGrainFieldBuilding.getId(), kafkaGrainFieldBuilding.getCondition(), kafkaGrainFieldBuilding.getMinCondition(),
                kafkaGrainFieldBuilding.getMaxCondition(), List.of(), List.of(),
                kafkaGrainFieldBuilding.getCreatedDate(), kafkaGrainFieldBuilding.getLastModifiedDate()
        );
    }
}
