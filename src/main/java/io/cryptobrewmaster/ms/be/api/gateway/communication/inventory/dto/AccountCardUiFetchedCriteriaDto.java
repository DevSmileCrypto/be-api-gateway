package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCardUiFetchedCriteriaDto {
    @NotBlank
    private String accountId;
    private Set<CardStatus> statuses;

    public static AccountCardUiFetchedCriteriaDto of(String accountId, Set<CardStatus> statuses) {
        return new AccountCardUiFetchedCriteriaDto(accountId, statuses);
    }
}
