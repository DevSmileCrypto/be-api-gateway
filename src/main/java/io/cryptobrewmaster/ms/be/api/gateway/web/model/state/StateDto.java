package io.cryptobrewmaster.ms.be.api.gateway.web.model.state;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.account.AccountStateDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.building.BuildingStateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateDto {
    @NotNull
    private AccountStateDto accountState;
    @NotNull
    private BuildingStateDto buildingState;
}
