package io.cryptobrewmaster.ms.be.api.gateway.kafka.building.academy.lab.yeast;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.AcademyYeastLabBuildingUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.craft.AcademyYeastLabBuildingCraftUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.dto.academy.lab.yeast.rent.AcademyYeastLabBuildingRentUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.KafkaAcademyYeastLabBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.craft.KafkaAcademyYeastLabBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.rent.KafkaAcademyYeastLabBuildingRent;
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
public class AcademyYeastLabBuildingKafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = "${kafka.topic.academy-yeast-lab-building-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.academy-yeast-lab-building-outcome}-${server.port}",
            containerFactory = "academyYeastLabBuildingConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAcademyYeastLabBuilding(ConsumerRecord<String, KafkaAcademyYeastLabBuilding> consumerRecord) {
        var kafkaAcademyYeastLabBuilding = consumerRecord.value();
        var academyYeastLabBuildingLogInfo = kafkaAcademyYeastLabBuilding.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome academy yeast lab building: {}", academyYeastLabBuildingLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaAcademyYeastLabBuilding.getAccountId(), "/topic/building/academy/lab/yeast",
                            AcademyYeastLabBuildingUiDto.of(kafkaAcademyYeastLabBuilding)
                    );
                    log.info("Processed message for outcome academy yeast lab building: {}", academyYeastLabBuildingLogInfo);
                },
                e -> log.error("Error while on consumed for outcome academy yeast lab building: {}. Error = {}",
                        academyYeastLabBuildingLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.academy-yeast-lab-building-rent-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.academy-yeast-lab-building-rent-outcome}-${server.port}",
            containerFactory = "academyYeastLabBuildingRentConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAcademyYeastLabBuildingRent(ConsumerRecord<String, KafkaAcademyYeastLabBuildingRent> consumerRecord) {
        var kafkaAcademyYeastLabBuildingRent = consumerRecord.value();
        var academyYeastLabBuildingRentLogInfo = kafkaAcademyYeastLabBuildingRent.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome academy yeast lab building rent: {}", academyYeastLabBuildingRentLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaAcademyYeastLabBuildingRent.getAccountId(), "/topic/building/academy/lab/yeast/rent",
                            AcademyYeastLabBuildingRentUiDto.of(kafkaAcademyYeastLabBuildingRent)
                    );
                    log.info("Processed message for outcome academy yeast lab building rent: {}", academyYeastLabBuildingRentLogInfo);
                },
                e -> log.error("Error while on consumed for outcome academy yeast lab building rent: {}. Error = {}",
                        academyYeastLabBuildingRentLogInfo, e.getMessage())
        );
    }

    @KafkaListener(
            topics = "${kafka.topic.academy-yeast-lab-building-craft-outcome}",
            groupId = "${kafka.config.group-id}",
            clientIdPrefix = "${kafka.config.client-id}-${kafka.topic.academy-yeast-lab-building-craft-outcome}-${server.port}",
            containerFactory = "academyYeastLabBuildingCraftConcurrentKafkaListenerContainerFactory"
    )
    public void consumeAcademyYeastLabBuildingCraft(ConsumerRecord<String, KafkaAcademyYeastLabBuildingCraft> consumerRecord) {
        var kafkaAcademyYeastLabBuildingCraft = consumerRecord.value();
        var academyYeastLabBuildingCraftLogInfo = kafkaAcademyYeastLabBuildingCraft.toString();

        KafkaConsumerMDCUtil.submit(
                consumerRecord,
                () -> {
                    log.info("Consumed message for outcome academy yeast lab building craft: {}", academyYeastLabBuildingCraftLogInfo);
                    messagingTemplate.convertAndSendToUser(
                            kafkaAcademyYeastLabBuildingCraft.getAccountId(), "/topic/building/academy/lab/yeast/craft",
                            AcademyYeastLabBuildingCraftUiDto.of(kafkaAcademyYeastLabBuildingCraft)
                    );
                    log.info("Processed message for outcome academy yeast lab building craft: {}", academyYeastLabBuildingCraftLogInfo);
                },
                e -> log.error("Error while on consumed for outcome academy yeast lab building craft: {}. Error = {}",
                        academyYeastLabBuildingCraftLogInfo, e.getMessage())
        );
    }

}
