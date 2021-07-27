package io.cryptobrewmaster.ms.be.api.gateway.web.model.state.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.AccountBalanceUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.AccountEnergyUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.AccountBeerCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.AccountResourceCardUiDto;
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
public class AccountStateDto {
    @NotNull
    @ToString.Exclude
    private List<AccountBalanceUiDto> accountBalances;
    @NotNull
    @ToString.Exclude
    private List<AccountEnergyUiDto> accountEnergies;
    @NotNull
    @ToString.Exclude
    private List<AccountResourceCardUiDto> accountResourceCards;
    @NotNull
    @ToString.Exclude
    private List<AccountBeerCardUiDto> accountBeerCards;
}
