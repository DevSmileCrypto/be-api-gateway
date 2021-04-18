package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.balance;

import io.cryptobrewmaster.ms.be.library.constants.EntityStatus;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.AccountBalanceKDto;
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
            topics = "${kafka.topic.account-balance-output}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-balance-output}-${server.port}",
            containerFactory = "accountBalanceConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAndOutput(ConsumerRecord<String, AccountBalanceKDto> consumerRecord) {
        log.debug("Consumed message for output account balance: Consumer record = {}", consumerRecord);

        AccountBalanceKDto accountBalanceKDto = consumerRecord.value();
        var accountBalanceLogInfo = accountBalanceKDto.toString();

        try {
            log.info("Consumed message for output account balance: {}", accountBalanceLogInfo);
            accountBalanceKafkaReceiver.output(accountBalanceKDto, consumerRecord.headers());
            log.info("Processed message for output account balance: {}", accountBalanceLogInfo);
        } catch (Exception e) {
            log.error("Error while on consumed for output account balance: {}. Error = {}",
                    accountBalanceLogInfo, e.getMessage());
        }
    }

}
