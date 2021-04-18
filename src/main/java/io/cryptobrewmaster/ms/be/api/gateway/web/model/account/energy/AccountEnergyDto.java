package io.cryptobrewmaster.ms.be.api.gateway.web.model.account.energy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.EnergyType;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.AccountEnergyKDto;
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

    public static AccountEnergyDto of(AccountEnergyKDto accountEnergyKDto) {
        return new AccountEnergyDto(
                accountEnergyKDto.getType(), accountEnergyKDto.getQuantity(),
                accountEnergyKDto.getRecoveryDate()
        );
    }
}
