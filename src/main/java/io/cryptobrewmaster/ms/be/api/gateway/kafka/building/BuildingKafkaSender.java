package io.cryptobrewmaster.ms.be.api.gateway.kafka.building;

import io.cryptobrewmaster.ms.be.api.gateway.configuration.kafka.properties.KafkaProperties;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.building.request.BuildingCraftChancesRequestUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.building.request.BuildingStateRequestUiDto;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingCraftChancesRequest;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingStateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BuildingKafkaSender {

    private final KafkaTemplate<String, KafkaBuildingStateRequest> buildingStateRequestKafkaTemplate;
    private final KafkaTemplate<String, KafkaBuildingCraftChancesRequest> buildingCraftChancesRequestKafkaTemplate;

    private final KafkaProperties kafkaProperties;

    public void determineState(String accountId, BuildingStateRequestUiDto buildingStateRequestUiDto) {
        var kafkaBuildingStateRequest = buildingStateRequestUiDto.generateKafkaDto(accountId);

        log.info("Send message to production building ms for determine building state: {}", kafkaBuildingStateRequest);

        buildingStateRequestKafkaTemplate.send(
                kafkaProperties.getTopic().getBuildingStateDetermine(),
                kafkaBuildingStateRequest.getAccountId(),
                kafkaBuildingStateRequest
        );
    }

    public void determineCraftChances(String accountId, BuildingCraftChancesRequestUiDto buildingCraftChancesRequestUiDto) {
        var kafkaBuildingCraftChancesRequest = buildingCraftChancesRequestUiDto.generateKafkaDto(accountId);

        log.info("Send message to production building ms for determine building craft chances: {}", kafkaBuildingCraftChancesRequest);

        buildingCraftChancesRequestKafkaTemplate.send(
                kafkaProperties.getTopic().getBuildingCraftChancesDetermine(),
                kafkaBuildingCraftChancesRequest.getAccountId(),
                kafkaBuildingCraftChancesRequest
        );
    }

}
