package io.cryptobrewmaster.ms.be.api.gateway.kafka.building;

import io.cryptobrewmaster.ms.be.api.gateway.web.model.building.response.BuildingCraftChancesUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.building.response.BuildingStateUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingCraftChances;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingState;
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
public class BuildingKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.building-state-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.building-state-outcome}-${server.port}",
            containerFactory = "buildingStateConcurrentKafkaListenerContainerFactory"
    )
    public void consumeState(ConsumerRecord<String, KafkaBuildingState> consumerRecord) {
        var kafkaBuildingState = consumerRecord.value();
        var buildingStateLogInfo = kafkaBuildingState.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome building state: {}", buildingStateLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaBuildingState.getAccountId(), "/topic/building/state",
                            BuildingStateUiDto.of(kafkaBuildingState)
                    );
                    log.info("Processed message for outcome building state: {}", buildingStateLogInfo);
                },
                e -> log.error("Error while on consumed for outcome building state: {}. Error = {}",
                        buildingStateLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.building-craft-chances-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.building-craft-chances-outcome}-${server.port}",
            containerFactory = "buildingCraftChancesConcurrentKafkaListenerContainerFactory"
    )
    public void consumeCraftChances(ConsumerRecord<String, KafkaBuildingCraftChances> consumerRecord) {
        var kafkaBuildingCraftChances = consumerRecord.value();
        var buildingCraftChancesLogInfo = kafkaBuildingCraftChances.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome building craft chances: {}", buildingCraftChancesLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaBuildingCraftChances.getAccountId(), "/topic/building/craft/chances",
                            BuildingCraftChancesUiDto.of(kafkaBuildingCraftChances)
                    );
                    log.info("Processed message for outcome building craft chances: {}", buildingCraftChancesLogInfo);
                },
                e -> log.error("Error while on consumed for outcome building craft chances: {}. Error = {}",
                        buildingCraftChancesLogInfo, e.getMessage())
        );
    }

}
