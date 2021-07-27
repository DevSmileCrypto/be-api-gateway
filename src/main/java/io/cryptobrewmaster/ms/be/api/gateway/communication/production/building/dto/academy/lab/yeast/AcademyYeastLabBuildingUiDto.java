package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.craft.AcademyYeastLabBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.rent.AcademyYeastLabBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.KafkaAcademyYeastLabBuilding;
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
public class AcademyYeastLabBuildingUiDto {
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
    private List<AcademyYeastLabBuildingCraftUiDto> academyYeastLabBuildingCrafts;
    @NotNull
    @ToString.Exclude
    private List<AcademyYeastLabBuildingRentUiDto> academyYeastLabBuildingRents;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static AcademyYeastLabBuildingUiDto of(KafkaAcademyYeastLabBuilding kafkaAcademyYeastLabBuilding) {
        return new AcademyYeastLabBuildingUiDto(
                kafkaAcademyYeastLabBuilding.getId(), kafkaAcademyYeastLabBuilding.getCondition(), kafkaAcademyYeastLabBuilding.getMinCondition(),
                kafkaAcademyYeastLabBuilding.getMaxCondition(), List.of(), List.of(),
                kafkaAcademyYeastLabBuilding.getCreatedDate(), kafkaAcademyYeastLabBuilding.getLastModifiedDate()
        );
    }
}
