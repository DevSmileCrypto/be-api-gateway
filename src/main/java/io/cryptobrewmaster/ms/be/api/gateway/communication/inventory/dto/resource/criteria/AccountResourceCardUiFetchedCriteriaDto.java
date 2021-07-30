package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResourceCardUiFetchedCriteriaDto {
    @NotNull
    private String accountId;
    private Set<CardStatus> statuses;

    public static AccountResourceCardUiFetchedCriteriaDto of(String accountId, Set<CardStatus> statuses) {
        return new AccountResourceCardUiFetchedCriteriaDto(accountId, statuses);
    }
}
