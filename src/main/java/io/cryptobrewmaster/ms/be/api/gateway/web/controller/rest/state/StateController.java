package io.cryptobrewmaster.ms.be.api.gateway.web.controller.rest.state;

import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.api.gateway.service.state.StateService;
import io.cryptobrewmaster.ms.be.api.gateway.web.model.state.StateUiDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/state")
@RestController
public class StateController {

    private final StateService stateService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public StateUiDto getState() {
        var authentication = (AccountAuthentication) SecurityContextHolder.getContext().getAuthentication();
        log.info("Request to get state received. Authentication = {}", authentication);
        var stateDto = stateService.getState(authentication);
        log.info("Response on get state. Authentication = {}, state = {}", authentication, stateDto);
        return stateDto;
    }

}
