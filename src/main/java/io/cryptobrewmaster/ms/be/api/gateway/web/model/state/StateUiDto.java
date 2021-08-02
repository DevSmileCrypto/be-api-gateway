package io.cryptobrewmaster.ms.be.api.gateway.web.model.state;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.AccountBuildingStateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.account.AccountStateUiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateUiDto {
    @NotNull
    private AccountStateUiDto accountState;
    @NotNull
    private AccountBuildingStateUiDto accountBuildingState;
}
