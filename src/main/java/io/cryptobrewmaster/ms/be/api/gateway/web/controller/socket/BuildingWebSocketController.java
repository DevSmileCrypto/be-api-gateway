package io.cryptobrewmaster.ms.be.api.gateway.web.controller.socket;

import io.cryptobrewmaster.ms.be.api.gateway.kafka.building.BuildingKafkaSender;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.building.request.BuildingCraftChancesRequestUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.building.request.BuildingStateRequestUiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BuildingWebSocketController {

    private final BuildingKafkaSender buildingKafkaSender;

    @MessageMapping("/building/state/determine")
    public void determineBuildingState(@Payload BuildingStateRequestUiDto buildingStateRequestUiDto,
                               Principal principal) {
        log.info("Request to determine building state received. {}", buildingStateRequestUiDto);
        buildingKafkaSender.determineState(principal.getName(), buildingStateRequestUiDto);
        log.info("Response on determine building state. {}", buildingStateRequestUiDto);
    }

    @MessageMapping("/building/craft/chance/determine")
    public void determineBuildingCraftChances(@Payload BuildingCraftChancesRequestUiDto buildingCraftChancesRequestUiDto,
                                             Principal principal) {
        log.info("Request to determine building craft chances received. {}", buildingCraftChancesRequestUiDto);
        buildingKafkaSender.determineCraftChances(principal.getName(), buildingCraftChancesRequestUiDto);
        log.info("Response on determine building craft chances. {}", buildingCraftChancesRequestUiDto);
    }

}
