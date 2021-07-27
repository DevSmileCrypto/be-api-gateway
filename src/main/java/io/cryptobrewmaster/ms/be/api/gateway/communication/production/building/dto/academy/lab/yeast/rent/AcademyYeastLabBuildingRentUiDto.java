package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.building.RentStatus;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.rent.KafkaAcademyYeastLabBuildingRent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcademyYeastLabBuildingRentUiDto {
    @NotNull
    private Long id;
    @NotNull
    private RentStatus status;
    @NotNull
    private Long rentTime;
    @NotNull
    private Long rentEndedDate;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static AcademyYeastLabBuildingRentUiDto of(KafkaAcademyYeastLabBuildingRent kafkaAcademyYeastLabBuildingRent) {
        return new AcademyYeastLabBuildingRentUiDto(
                kafkaAcademyYeastLabBuildingRent.getId(), kafkaAcademyYeastLabBuildingRent.getStatus(),
                kafkaAcademyYeastLabBuildingRent.getRentTime(), kafkaAcademyYeastLabBuildingRent.getRentEndedDate(),
                kafkaAcademyYeastLabBuildingRent.getCreatedDate(), kafkaAcademyYeastLabBuildingRent.getLastModifiedDate()
        );
    }
}
