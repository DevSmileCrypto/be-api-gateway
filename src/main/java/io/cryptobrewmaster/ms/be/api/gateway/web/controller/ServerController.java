package io.cryptobrewmaster.ms.be.api.gateway.web.controller;

import io.cryptobrewmaster.ms.be.api.gateway.service.ServerService;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.server.ServerOperationDto;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/server")
@RestController
public class ServerController {

    private final ServerService serverService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/properties/refresh")
    public List<ServerOperationDto> refreshServerProperties(@Valid @NotNull @RequestBody List<MicroServiceName> microServiceNames) {
        log.info("Request to refresh server properties received. {}", microServiceNames);
        List<ServerOperationDto> serverOperationDtos = serverService.refreshServersProperties(microServiceNames);
        log.info("Response on refresh server properties. {}", serverOperationDtos);
        return serverOperationDtos;
    }

}
