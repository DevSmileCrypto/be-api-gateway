package io.cryptobrewmaster.ms.be.api.gateway.kafka.building.house.brew;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.BrewHouseBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.brew.craft.BrewHouseBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.brew.KafkaBrewHouseBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.brew.craft.KafkaBrewHouseBuildingCraft;
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
public class BrewHouseBuildingKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.brew-house-building-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.brew-house-building-outcome}-${server.port}",
            containerFactory = "brewHouseBuildingConcurrentKafkaListenerContainerFactory"
    )
    public void consumeBrewHouseBuilding(ConsumerRecord<String, KafkaBrewHouseBuilding> consumerRecord) {
        var kafkaBrewHouseBuilding = consumerRecord.value();
        var brewHouseBuildingLogInfo = kafkaBrewHouseBuilding.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome brew house building: {}", brewHouseBuildingLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaBrewHouseBuilding.getAccountId(), "/topic/building/house/brew",
                            BrewHouseBuildingUiDto.of(kafkaBrewHouseBuilding)
                    );
                    log.info("Processed message for outcome brew house building: {}", brewHouseBuildingLogInfo);
                },
                e -> log.error("Error while on consumed for outcome brew house building: {}. Error = {}",
                        brewHouseBuildingLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.brew-house-building-craft-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.brew-house-building-craft-outcome}-${server.port}",
            containerFactory = "brewHouseBuildingCraftConcurrentKafkaListenerContainerFactory"
    )
    public void consumeBrewHouseBuildingCraft(ConsumerRecord<String, KafkaBrewHouseBuildingCraft> consumerRecord) {
        var kafkaBrewHouseBuildingCraft = consumerRecord.value();
        var brewHouseBuildingCraftLogInfo = kafkaBrewHouseBuildingCraft.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome brew house building craft: {}", brewHouseBuildingCraftLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaBrewHouseBuildingCraft.getAccountId(), "/topic/building/house/brew/craft",
                            BrewHouseBuildingCraftUiDto.of(kafkaBrewHouseBuildingCraft)
                    );
                    log.info("Processed message for outcome brew house building craft: {}", brewHouseBuildingCraftLogInfo);
                },
                e -> log.error("Error while on consumed for outcome brew house building craft: {}. Error = {}",
                        brewHouseBuildingCraftLogInfo, e.getMessage())
        );
    }

}
