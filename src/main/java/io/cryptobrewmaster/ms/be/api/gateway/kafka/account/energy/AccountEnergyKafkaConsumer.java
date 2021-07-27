package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.energy;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.AccountEnergyUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.KafkaAccountEnergy;
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
public class AccountEnergyKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.account-energy-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.account-energy-outcome}-${server.port}",
            containerFactory = "accountEnergyConcurrentKafkaListenerContainerFactory"
    )
    public void consume(ConsumerRecord<String, KafkaAccountEnergy> consumerRecord) {
        var kafkaAccountEnergy = consumerRecord.value();
        var accountEnergyLogInfo = kafkaAccountEnergy.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome account energy: {}", accountEnergyLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaAccountEnergy.getAccountId(), "/topic/account/energy",
                            AccountEnergyUiDto.of(kafkaAccountEnergy)
                    );
                    log.info("Processed message for outcome account energy: {}", accountEnergyLogInfo);
                },
                e -> log.error("Error while on consumed for outcome account energy: {}. Error = {}",
                        accountEnergyLogInfo, e.getMessage())
        );
    }

}
