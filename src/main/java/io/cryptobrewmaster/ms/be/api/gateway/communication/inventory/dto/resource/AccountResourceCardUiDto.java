package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.card.CardQuality;
import io.cryptobrewmaster.ms.be.library.constants.card.CardStatus;
import io.cryptobrewmaster.ms.be.library.constants.card.resource.ResourceCardName;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.resource.KafkaAccountResourceCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResourceCardUiDto {
    @NotNull
    private Long id;
    @NotNull
    private ResourceCardName cardName;
    @NotNull
    private CardQuality quality;
    @NotNull
    private CardStatus status;
    @NotNull
    private Long createdDate;
    @NotNull
    private Long lastModifiedDate;

    public static AccountResourceCardUiDto of(KafkaAccountResourceCard kafkaAccountResourceCard) {
        return new AccountResourceCardUiDto(
                kafkaAccountResourceCard.getId(), kafkaAccountResourceCard.getCardName(), kafkaAccountResourceCard.getQuality(),
                kafkaAccountResourceCard.getStatus(), kafkaAccountResourceCard.getCreatedDate(), kafkaAccountResourceCard.getLastModifiedDate()
        );
    }
}
