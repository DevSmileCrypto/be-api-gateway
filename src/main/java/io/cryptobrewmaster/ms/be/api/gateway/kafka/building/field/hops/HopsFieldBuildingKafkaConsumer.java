package io.cryptobrewmaster.ms.be.api.gateway.kafka.building.field.hops;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.HopsFieldBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.craft.HopsFieldBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.field.hops.rent.HopsFieldBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.KafkaHopsFieldBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.craft.KafkaHopsFieldBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.rent.KafkaHopsFieldBuildingRent;
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
public class HopsFieldBuildingKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.hops-field-building-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.hops-field-building-outcome}-${server.port}",
            containerFactory = "hopsFieldBuildingConcurrentKafkaListenerContainerFactory"
    )
    public void consumeHopsFieldBuilding(ConsumerRecord<String, KafkaHopsFieldBuilding> consumerRecord) {
        var kafkaHopsFieldBuilding = consumerRecord.value();
        var hopsFieldBuildingLogInfo = kafkaHopsFieldBuilding.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome hops field building: {}", hopsFieldBuildingLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaHopsFieldBuilding.getAccountId(), "/topic/building/field/hops",
                            HopsFieldBuildingUiDto.of(kafkaHopsFieldBuilding)
                    );
                    log.info("Processed message for outcome hops field building: {}", hopsFieldBuildingLogInfo);
                },
                e -> log.error("Error while on consumed for outcome hops field building: {}. Error = {}",
                        hopsFieldBuildingLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.hops-field-building-rent-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.hops-field-building-rent-outcome}-${server.port}",
            containerFactory = "hopsFieldBuildingRentConcurrentKafkaListenerContainerFactory"
    )
    public void consumeHopsFieldBuildingRent(ConsumerRecord<String, KafkaHopsFieldBuildingRent> consumerRecord) {
        var kafkaHopsFieldBuildingRent = consumerRecord.value();
        var hopsFieldBuildingRentLogInfo = kafkaHopsFieldBuildingRent.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome hops field building rent: {}", hopsFieldBuildingRentLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaHopsFieldBuildingRent.getAccountId(), "/topic/building/field/hops/rent",
                            HopsFieldBuildingRentUiDto.of(kafkaHopsFieldBuildingRent)
                    );
                    log.info("Processed message for outcome hops field building rent: {}", hopsFieldBuildingRentLogInfo);
                },
                e -> log.error("Error while on consumed for outcome hops field building rent: {}. Error = {}",
                        hopsFieldBuildingRentLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.hops-field-building-craft-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.hops-field-building-craft-outcome}-${server.port}",
            containerFactory = "hopsFieldBuildingCraftConcurrentKafkaListenerContainerFactory"
    )
    public void consumeHopsFieldBuildingCraft(ConsumerRecord<String, KafkaHopsFieldBuildingCraft> consumerRecord) {
        var kafkaHopsFieldBuildingCraft = consumerRecord.value();
        var hopsFieldBuildingCraftLogInfo = kafkaHopsFieldBuildingCraft.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome hops field building craft: {}", hopsFieldBuildingCraftLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaHopsFieldBuildingCraft.getAccountId(), "/topic/building/field/hops/craft",
                            HopsFieldBuildingCraftUiDto.of(kafkaHopsFieldBuildingCraft)
                    );
                    log.info("Processed message for outcome hops field building craft: {}", hopsFieldBuildingCraftLogInfo);
                },
                e -> log.error("Error while on consumed for outcome hops field building craft: {}. Error = {}",
                        hopsFieldBuildingCraftLogInfo, e.getMessage())
        );
    }

}
