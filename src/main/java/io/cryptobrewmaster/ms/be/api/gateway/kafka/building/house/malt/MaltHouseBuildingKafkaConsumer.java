package io.cryptobrewmaster.ms.be.api.gateway.kafka.building.house.malt;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.MaltHouseBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.craft.MaltHouseBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.house.malt.rent.MaltHouseBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.KafkaMaltHouseBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.craft.KafkaMaltHouseBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.rent.KafkaMaltHouseBuildingRent;
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
public class MaltHouseBuildingKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.malt-house-building-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.malt-house-building-outcome}-${server.port}",
            containerFactory = "maltHouseBuildingConcurrentKafkaListenerContainerFactory"
    )
    public void consumeMaltHouseBuilding(ConsumerRecord<String, KafkaMaltHouseBuilding> consumerRecord) {
        var kafkaMaltHouseBuilding = consumerRecord.value();
        var maltHouseBuildingLogInfo = kafkaMaltHouseBuilding.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome malt house building: {}", maltHouseBuildingLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaMaltHouseBuilding.getAccountId(), "/topic/building/house/malt",
                            MaltHouseBuildingUiDto.of(kafkaMaltHouseBuilding)
                    );
                    log.info("Processed message for outcome malt house building: {}", maltHouseBuildingLogInfo);
                },
                e -> log.error("Error while on consumed for outcome malt house building: {}. Error = {}",
                        maltHouseBuildingLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.malt-house-building-rent-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.malt-house-building-rent-outcome}-${server.port}",
            containerFactory = "maltHouseBuildingRentConcurrentKafkaListenerContainerFactory"
    )
    public void consumeMaltHouseBuildingRent(ConsumerRecord<String, KafkaMaltHouseBuildingRent> consumerRecord) {
        var kafkaMaltHouseBuildingRent = consumerRecord.value();
        var maltHouseBuildingRentLogInfo = kafkaMaltHouseBuildingRent.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome malt house building rent: {}", maltHouseBuildingRentLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaMaltHouseBuildingRent.getAccountId(), "/topic/building/house/malt/rent",
                            MaltHouseBuildingRentUiDto.of(kafkaMaltHouseBuildingRent)
                    );
                    log.info("Processed message for outcome malt house building rent: {}", maltHouseBuildingRentLogInfo);
                },
                e -> log.error("Error while on consumed for outcome malt house building rent: {}. Error = {}",
                        maltHouseBuildingRentLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.malt-house-building-craft-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.malt-house-building-craft-outcome}-${server.port}",
            containerFactory = "maltHouseBuildingCraftConcurrentKafkaListenerContainerFactory"
    )
    public void consumeMaltHouseBuildingCraft(ConsumerRecord<String, KafkaMaltHouseBuildingCraft> consumerRecord) {
        var kafkaMaltHouseBuildingCraft = consumerRecord.value();
        var maltHouseBuildingCraftLogInfo = kafkaMaltHouseBuildingCraft.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome malt house building craft: {}", maltHouseBuildingCraftLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaMaltHouseBuildingCraft.getAccountId(), "/topic/building/house/malt/craft",
                            MaltHouseBuildingCraftUiDto.of(kafkaMaltHouseBuildingCraft)
                    );
                    log.info("Processed message for outcome malt house building craft: {}", maltHouseBuildingCraftLogInfo);
                },
                e -> log.error("Error while on consumed for outcome malt house building craft: {}. Error = {}",
                        maltHouseBuildingCraftLogInfo, e.getMessage())
        );
    }

}
