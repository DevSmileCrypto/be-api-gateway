package io.cryptobrewmaster.ms.be.api.gateway.web.model.account.balance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.Currency;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.KafkaAccountBalance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalanceDto {
    @NotBlank
    private Currency currency;
    @NotBlank
    private Double quantity;

    public static AccountBalanceDto of(KafkaAccountBalance kafkaAccountBalance) {
        return new AccountBalanceDto(kafkaAccountBalance.getCurrency(), kafkaAccountBalance.getQuantity().doubleValue());
    }
}
