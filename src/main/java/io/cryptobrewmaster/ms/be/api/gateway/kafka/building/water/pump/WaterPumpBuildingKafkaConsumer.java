package io.cryptobrewmaster.ms.be.api.gateway.kafka.building.water.pump;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.WaterPumpBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.craft.WaterPumpBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.water.pump.rent.WaterPumpBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.KafkaWaterPumpBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.craft.KafkaWaterPumpBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.rent.KafkaWaterPumpBuildingRent;
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
public class WaterPumpBuildingKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.water-pump-building-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.water-pump-building-outcome}-${server.port}",
            containerFactory = "waterPumpBuildingConcurrentKafkaListenerContainerFactory"
    )
    public void consumeWaterPumpBuilding(ConsumerRecord<String, KafkaWaterPumpBuilding> consumerRecord) {
        var kafkaWaterPumpBuilding = consumerRecord.value();
        var waterPumpBuildingLogInfo = kafkaWaterPumpBuilding.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome water pump building: {}", waterPumpBuildingLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaWaterPumpBuilding.getAccountId(), "/topic/building/water-pump",
                            WaterPumpBuildingUiDto.of(kafkaWaterPumpBuilding)
                    );
                    log.info("Processed message for outcome water pump building: {}", waterPumpBuildingLogInfo);
                },
                e -> log.error("Error while on consumed for outcome water pump building: {}. Error = {}",
                        waterPumpBuildingLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.water-pump-building-rent-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.water-pump-building-rent-outcome}-${server.port}",
            containerFactory = "waterPumpBuildingRentConcurrentKafkaListenerContainerFactory"
    )
    public void consumeWaterPumpBuildingRent(ConsumerRecord<String, KafkaWaterPumpBuildingRent> consumerRecord) {
        var kafkaWaterPumpBuildingRent = consumerRecord.value();
        var waterPumpBuildingRentLogInfo = kafkaWaterPumpBuildingRent.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome water pump building rent: {}", waterPumpBuildingRentLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaWaterPumpBuildingRent.getAccountId(), "/topic/building/water-pump/rent",
                            WaterPumpBuildingRentUiDto.of(kafkaWaterPumpBuildingRent)
                    );
                    log.info("Processed message for outcome water pump building rent: {}", waterPumpBuildingRentLogInfo);
                },
                e -> log.error("Error while on consumed for outcome water pump building rent: {}. Error = {}",
                        waterPumpBuildingRentLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.water-pump-building-craft-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.water-pump-building-craft-outcome}-${server.port}",
            containerFactory = "waterPumpBuildingCraftConcurrentKafkaListenerContainerFactory"
    )
    public void consumeWaterPumpBuildingCraft(ConsumerRecord<String, KafkaWaterPumpBuildingCraft> consumerRecord) {
        var kafkaWaterPumpBuildingCraft = consumerRecord.value();
        var waterPumpBuildingCraftLogInfo = kafkaWaterPumpBuildingCraft.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome water pump building craft: {}", waterPumpBuildingCraftLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaWaterPumpBuildingCraft.getAccountId(), "/topic/building/water-pump/craft",
                            WaterPumpBuildingCraftUiDto.of(kafkaWaterPumpBuildingCraft)
                    );
                    log.info("Processed message for outcome water pump building craft: {}", waterPumpBuildingCraftLogInfo);
                },
                e -> log.error("Error while on consumed for outcome water pump building craft: {}. Error = {}",
                        waterPumpBuildingCraftLogInfo, e.getMessage())
        );
    }

}
