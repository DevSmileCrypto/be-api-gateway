package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.energy;

import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.AccountEnergyKDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountEnergyKafkaConsumer {

    private final AccountEnergyKafkaReceiver accountEnergyKafkaReceiver;

    @KafkaListener(
            topics = "${kafka.topic.account-energy-output}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-energy-output}-${server.port}",
            containerFactory = "accountEnergyConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAndSave(ConsumerRecord<String, AccountEnergyKDto> consumerRecord) {
        log.debug("Consumed message for output account energy: Consumer record = {}", consumerRecord);

        AccountEnergyKDto accountEnergyKDto = consumerRecord.value();
        var accountEnergyLogInfo = accountEnergyKDto.toString();

        try {
            log.info("Consumed message for output account energy: {}", accountEnergyLogInfo);
            accountEnergyKafkaReceiver.output(accountEnergyKDto, consumerRecord.headers());
            log.info("Processed message for output account energy: {}", accountEnergyLogInfo);
        } catch (Exception e) {
            log.error("Error while on consumed for output account energy: {}. Error = {}",
                    accountEnergyLogInfo, e.getMessage());
        }
    }

}
