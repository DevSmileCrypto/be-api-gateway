package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.balance;

import io.cryptobrewmaster.ms.be.library.constants.EntityStatus;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.KafkaAccountBalance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountBalanceKafkaConsumer {

    private final AccountBalanceKafkaReceiver accountBalanceKafkaReceiver;

    @KafkaListener(
            topics = "${kafka.topic.account-balance-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-balance-outcome}-${server.port}",
            containerFactory = "accountBalanceConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAndOutput(ConsumerRecord<String, KafkaAccountBalance> consumerRecord) {
        log.debug("Consumed message for outcome account balance: Consumer record = {}", consumerRecord);

        var kafkaAccountBalance = consumerRecord.value();
        var accountBalanceLogInfo = kafkaAccountBalance.toString();

        try {
            log.info("Consumed message for outcome account balance: {}", accountBalanceLogInfo);
            accountBalanceKafkaReceiver.outcome(kafkaAccountBalance, consumerRecord.headers());
            log.info("Processed message for outcome account balance: {}", accountBalanceLogInfo);
        } catch (Exception e) {
            log.error("Error while on consumed for outcome account balance: {}. Error = {}",
                    accountBalanceLogInfo, e.getMessage());
        }
    }

}