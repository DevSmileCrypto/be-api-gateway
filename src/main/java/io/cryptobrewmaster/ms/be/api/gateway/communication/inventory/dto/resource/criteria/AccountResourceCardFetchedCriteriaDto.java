package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.criteria;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResourceCardFetchedCriteriaDto {
    private Set<Long> ids;
    private String accountId;
    private Set<CardStatus> statuses;
    private Integer page;
    private Integer size;

    public static AccountResourceCardFetchedCriteriaDto of(String accountId, Set<CardStatus> statuses) {
        return new AccountResourceCardFetchedCriteriaDto(null, accountId, statuses, null, null);
    }
}
