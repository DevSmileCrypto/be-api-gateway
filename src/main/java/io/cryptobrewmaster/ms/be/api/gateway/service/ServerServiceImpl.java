package io.cryptobrewmaster.ms.be.api.gateway.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.service.AccountCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service.AuthenticationCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.server.ServerOperationDto;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServerServiceImpl implements ServerService {

    private final AuthenticationCommunicationService authenticationCommunicationService;
    private final AccountCommunicationService accountCommunicationService;

    private EnumMap<MicroServiceName, Runnable> refreshServersPropertiesMap = new EnumMap<>(MicroServiceName.class);

    @PostConstruct
    public void init() {
        this.refreshServersPropertiesMap = new EnumMap<>(Map.of(
                MicroServiceName.BE_AUTHENTICATION, authenticationCommunicationService::refreshServerProperties,
                MicroServiceName.BE_ACCOUNT, accountCommunicationService::refreshServerProperties
        ));
    }

    @Override
    public List<ServerOperationDto> refreshServersProperties(List<MicroServiceName> microServiceNames) {
        return microServiceNames.stream()
                .map(microServiceName -> {
                    try {
                        refreshServersPropertiesMap.get(microServiceName).run();
                        return new ServerOperationDto(microServiceName, true);
                    } catch (Exception e) {
                        log.warn("Error while on refresh {} server properties. Error = {}", microServiceName, e.getMessage());
                        return new ServerOperationDto(microServiceName, false);
                    }
                })
                .collect(Collectors.toList());
    }

}
