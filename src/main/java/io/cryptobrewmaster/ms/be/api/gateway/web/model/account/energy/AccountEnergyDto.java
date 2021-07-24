package io.cryptobrewmaster.ms.be.api.gateway.web.model.account.energy;

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
public class AccountEnergyDto {
    @NotNull
    private EnergyType type;
    @NotNull
    private Double quantity;
    @NotNull
    private Long recoveryDate;

    public static AccountEnergyDto of(KafkaAccountEnergy kafkaAccountEnergy) {
        return new AccountEnergyDto(
                kafkaAccountEnergy.getType(), kafkaAccountEnergy.getQuantity(),
                kafkaAccountEnergy.getRecoveryDate()
        );
    }
}
