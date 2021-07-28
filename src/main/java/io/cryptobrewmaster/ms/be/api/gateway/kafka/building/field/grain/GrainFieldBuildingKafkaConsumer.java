package io.cryptobrewmaster.ms.be.api.gateway.kafka.building.field.grain;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.GrainFieldBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.craft.GrainFieldBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.grain.rent.GrainFieldBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.KafkaGrainFieldBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.craft.KafkaGrainFieldBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.rent.KafkaGrainFieldBuildingRent;
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
public class GrainFieldBuildingKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.grain-field-building-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.grain-field-building-outcome}-${server.port}",
            containerFactory = "grainFieldBuildingConcurrentKafkaListenerContainerFactory"
    )
    public void consumeGrainFieldBuilding(ConsumerRecord<String, KafkaGrainFieldBuilding> consumerRecord) {
        var kafkaGrainFieldBuilding = consumerRecord.value();
        var grainFieldBuildingLogInfo = kafkaGrainFieldBuilding.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome grain field building: {}", grainFieldBuildingLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaGrainFieldBuilding.getAccountId(), "/topic/building/field/grain",
                            GrainFieldBuildingUiDto.of(kafkaGrainFieldBuilding)
                    );
                    log.info("Processed message for outcome grain field building: {}", grainFieldBuildingLogInfo);
                },
                e -> log.error("Error while on consumed for outcome grain field building: {}. Error = {}",
                        grainFieldBuildingLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.grain-field-building-rent-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.grain-field-building-rent-outcome}-${server.port}",
            containerFactory = "grainFieldBuildingRentConcurrentKafkaListenerContainerFactory"
    )
    public void consumeGrainFieldBuildingRent(ConsumerRecord<String, KafkaGrainFieldBuildingRent> consumerRecord) {
        var kafkaGrainFieldBuildingRent = consumerRecord.value();
        var grainFieldBuildingRentLogInfo = kafkaGrainFieldBuildingRent.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome grain field building rent: {}", grainFieldBuildingRentLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaGrainFieldBuildingRent.getAccountId(), "/topic/building/field/grain/rent",
                            GrainFieldBuildingRentUiDto.of(kafkaGrainFieldBuildingRent)
                    );
                    log.info("Processed message for outcome grain field building rent: {}", grainFieldBuildingRentLogInfo);
                },
                e -> log.error("Error while on consumed for outcome grain field building rent: {}. Error = {}",
                        grainFieldBuildingRentLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.grain-field-building-craft-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.grain-field-building-craft-outcome}-${server.port}",
            containerFactory = "grainFieldBuildingCraftConcurrentKafkaListenerContainerFactory"
    )
    public void consumeGrainFieldBuildingCraft(ConsumerRecord<String, KafkaGrainFieldBuildingCraft> consumerRecord) {
        var kafkaGrainFieldBuildingCraft = consumerRecord.value();
        var grainFieldBuildingCraftLogInfo = kafkaGrainFieldBuildingCraft.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome grain field building craft: {}", grainFieldBuildingCraftLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaGrainFieldBuildingCraft.getAccountId(), "/topic/building/field/grain/craft",
                            GrainFieldBuildingCraftUiDto.of(kafkaGrainFieldBuildingCraft)
                    );
                    log.info("Processed message for outcome grain field building craft: {}", grainFieldBuildingCraftLogInfo);
                },
                e -> log.error("Error while on consumed for outcome grain field building craft: {}. Error = {}",
                        grainFieldBuildingCraftLogInfo, e.getMessage())
        );
    }

}
