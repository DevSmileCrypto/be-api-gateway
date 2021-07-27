package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.balance;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.AccountBalanceUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.KafkaAccountBalance;
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
public class AccountBalanceKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.account-balance-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-balance-outcome}-${server.port}",
            containerFactory = "accountBalanceConcurrentKafkaListenerContainerFactory"
    )
    public void consume(ConsumerRecord<String, KafkaAccountBalance> consumerRecord) {
        var kafkaAccountBalance = consumerRecord.value();
        var accountBalanceLogInfo = kafkaAccountBalance.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome account balance: {}", accountBalanceLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaAccountBalance.getAccountId(), "/topic/account/balance",
                            AccountBalanceUiDto.of(kafkaAccountBalance)
                    );
                    log.info("Processed message for outcome account balance: {}", accountBalanceLogInfo);
                },
                e -> log.error("Error while on consumed for outcome account balance: {}. Error = {}",
                        accountBalanceLogInfo, e.getMessage())
        );
    }

}
