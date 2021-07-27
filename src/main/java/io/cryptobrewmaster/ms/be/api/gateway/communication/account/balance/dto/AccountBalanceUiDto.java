package io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.Currency;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.KafkaAccountBalance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalanceUiDto {
    @NotNull
    private Long id;
    @NotNull
    private Currency currency;
    @NotNull
    private Double quantity;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static AccountBalanceUiDto of(KafkaAccountBalance kafkaAccountBalance) {
        return new AccountBalanceUiDto(
                kafkaAccountBalance.getId(), kafkaAccountBalance.getCurrency(), kafkaAccountBalance.getQuantity().doubleValue(),
                kafkaAccountBalance.getCreatedDate(), kafkaAccountBalance.getLastModifiedDate()
        );
    }
}
