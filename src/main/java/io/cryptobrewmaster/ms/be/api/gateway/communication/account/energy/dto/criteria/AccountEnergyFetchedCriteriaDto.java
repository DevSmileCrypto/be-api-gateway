package io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.account.energy.EnergyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountEnergyFetchedCriteriaDto {
    private String accountId;
    private List<EnergyType> types;

    public static AccountEnergyFetchedCriteriaDto of(String accountId) {
        return new AccountEnergyFetchedCriteriaDto(accountId, null);
    }

    public static AccountEnergyFetchedCriteriaDto of(String accountId, List<EnergyType> types) {
        return new AccountEnergyFetchedCriteriaDto(accountId, types);
    }
}
