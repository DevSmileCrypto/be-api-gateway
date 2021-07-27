package io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.account.energy.EnergyType;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.KafkaAccountEnergy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountEnergyUiDto {
    @NotNull
    private Long id;
    @NotNull
    private EnergyType type;
    @NotNull
    private Double quantity;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static AccountEnergyUiDto of(KafkaAccountEnergy kafkaAccountEnergy) {
        return new AccountEnergyUiDto(
                kafkaAccountEnergy.getId(), kafkaAccountEnergy.getType(),
                kafkaAccountEnergy.getQuantity(), kafkaAccountEnergy.getCreatedDate(),
                kafkaAccountEnergy.getLastModifiedDate()
        );
    }
}
