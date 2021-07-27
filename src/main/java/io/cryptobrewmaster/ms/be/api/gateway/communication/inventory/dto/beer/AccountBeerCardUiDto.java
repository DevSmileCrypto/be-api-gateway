package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.CardStatus;
import io.cryptobrewmaster.ms.be.library.constants.card.beer.BeerCardName;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.beer.KafkaAccountBeerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBeerCardUiDto {
    @NotNull
    private Long id;
    @NotNull
    private BeerCardName cardName;
    @NotNull
    private CardQuality quality;
    @NotNull
    private CardStatus status;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static AccountBeerCardUiDto of(KafkaAccountBeerCard kafkaAccountBeerCard) {
        return new AccountBeerCardUiDto(
                kafkaAccountBeerCard.getId(), kafkaAccountBeerCard.getCardName(), kafkaAccountBeerCard.getQuality(),
                kafkaAccountBeerCard.getStatus(), kafkaAccountBeerCard.getCreatedDate(), kafkaAccountBeerCard.getLastModifiedDate()
        );
    }
}
