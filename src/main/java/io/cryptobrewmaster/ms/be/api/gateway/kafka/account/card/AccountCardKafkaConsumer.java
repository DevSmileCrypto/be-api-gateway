package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.card;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.AccountBeerCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.AccountResourceCardUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.beer.KafkaAccountBeerCard;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.resource.KafkaAccountResourceCard;
import io.cryptobrewmaster.ms.be.library.util.KafkaConsumerMDCUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountCardKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.account-resource-card-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-resource-card-outcome}-${server.port}",
            containerFactory = "accountResourceCardConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAccountResourceCard(ConsumerRecord<String, KafkaAccountResourceCard> consumerRecord) {
        var kafkaAccountResourceCard = consumerRecord.value();
        var accountResourceCardLogInfo = kafkaAccountResourceCard.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome account resource card: {}", accountResourceCardLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaAccountResourceCard.getAccountId(), "/topic/account/resource/card",
                            AccountResourceCardUiDto.of(kafkaAccountResourceCard)
                    );
                    log.info("Processed message for outcome account resource card: {}", accountResourceCardLogInfo);
                },
                e -> log.error("Error while on consumed for outcome account resource card: {}. Error = {}",
                        accountResourceCardLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.account-beer-card-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-beer-card-outcome}-${server.port}",
            containerFactory = "accountBeerCardConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAccountBeerCard(ConsumerRecord<String, KafkaAccountBeerCard> consumerRecord) {
        var kafkaAccountBeerCard = consumerRecord.value();
        var accountBeerCardLogInfo = kafkaAccountBeerCard.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome account beer card: {}", accountBeerCardLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaAccountBeerCard.getAccountId(), "/topic/account/beer/card",
                            AccountBeerCardUiDto.of(kafkaAccountBeerCard)
                    );
                    log.info("Processed message for outcome account beer card: {}", accountBeerCardLogInfo);
                },
                e -> log.error("Error while on consumed for outcome account beer card: {}. Error = {}",
                        accountBeerCardLogInfo, e.getMessage())
        );
    }

}
