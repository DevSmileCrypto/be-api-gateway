package io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalanceFetchedCriteriaDto {
    private String accountId;
    private List<Currency> currencies;

    public static AccountBalanceFetchedCriteriaDto of(String accountId) {
        return new AccountBalanceFetchedCriteriaDto(accountId, null);
    }

    public static AccountBalanceFetchedCriteriaDto of(String accountId, List<Currency> currencies) {
        return new AccountBalanceFetchedCriteriaDto(accountId, currencies);
    }
}
