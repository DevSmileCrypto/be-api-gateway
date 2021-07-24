package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.energy;

import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.KafkaAccountEnergy;
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
            topics = "${kafka.topic.account-energy-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-energy-outcome}-${server.port}",
            containerFactory = "accountEnergyConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAndSave(ConsumerRecord<String, KafkaAccountEnergy> consumerRecord) {
        log.debug("Consumed message for outcome account energy: Consumer record = {}", consumerRecord);

        var kafkaAccountEnergy = consumerRecord.value();
        var accountEnergyLogInfo = kafkaAccountEnergy.toString();

        try {
            log.info("Consumed message for outcome account energy: {}", accountEnergyLogInfo);
            accountEnergyKafkaReceiver.outcome(kafkaAccountEnergy, consumerRecord.headers());
            log.info("Processed message for outcome account energy: {}", accountEnergyLogInfo);
        } catch (Exception e) {
            log.error("Error while on consumed for outcome account energy: {}. Error = {}",
                    accountEnergyLogInfo, e.getMessage());
        }
    }

}
