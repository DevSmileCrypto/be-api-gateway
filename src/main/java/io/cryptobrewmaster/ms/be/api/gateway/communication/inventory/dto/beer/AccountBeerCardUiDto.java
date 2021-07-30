package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.CardStatus;
import io.cryptobrewmaster.ms.be.library.constants.card.beer.BeerCardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBeerCardUiDto {
    @NotNull
    private CardQuality quality;
    @NotNull
    private BeerCardName cardName;
    @NotNull
    private List<Card> cards;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Card {
        @NotNull
        private Long id;
        @NotNull
        private CardStatus status;
        @NotNull
        private Long createdDate;
        @NotNull
        private Long lastModifiedDate;
    }
}
