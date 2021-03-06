package io.cryptobrewmaster.ms.be.api.gateway.web.controller.rest.authentication;

import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.dto.AuthenticationTokenPairDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.dto.RegistrationOrLoginDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service.AuthenticationCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/authentication" )
@RestController
public class AuthenticationController {

    private final AuthenticationCommunicationService authenticationCommunicationService;

    @PostMapping("/login/keychain" )
    public AuthenticationTokenPairDto registrationOrLoginByKeychain(@Valid @NotNull @RequestBody RegistrationOrLoginDto registrationOrLoginDto) {
        log.info("Request to registration or login account by keychain received. {}", registrationOrLoginDto);
        var authenticationTokenPairDto = authenticationCommunicationService.loginHiveKeychain(registrationOrLoginDto);
        log.info("Response on registration or login account by keychain. {}", authenticationTokenPairDto);
        return authenticationTokenPairDto;
    }

    @GetMapping("/login/signer" )
    public RedirectView generateRegistrationOrLoginUrlBySigner() {
        log.info("Request to generate oauth registration or login url account by signer received." );
        String redirectUrl = authenticationCommunicationService.loginHiveSigner();
        log.info("Response on generate oauth registration or login url account by signer. Redirect url = {}", redirectUrl);
        return new RedirectView(redirectUrl);
    }

    @GetMapping("/login/signer/redirect" )
    public RedirectView completeRegistrationOrLoginBySigner(@Valid @NotNull @RequestParam MultiValueMap<String, String> params) {
        log.info("Request to complete redirect oauth registration or login account by signer received." );
        String redirectUrl = authenticationCommunicationService.redirectHiveSignerLogin(params);
        log.info("Response on complete redirect oauth registration or login account by signer. Redirect url = {}", redirectUrl);
        return new RedirectView(redirectUrl);
    }

    @PutMapping("/refresh/{refreshToken}" )
    public AuthenticationTokenPairDto refresh(@Valid @NotBlank @PathVariable String refreshToken) {
        log.info("Request to refresh account token pair received. Refresh token = {}", refreshToken);
        var authenticationTokenPairDto = authenticationCommunicationService.refreshTokenPair(refreshToken);
        log.info("Response on refresh account token pair. {}", authenticationTokenPairDto);
        return authenticationTokenPairDto;
    }

    @PreAuthorize("hasRole('USER')" )
    @PutMapping("/logout" )
    public void logout() {
        var accountId = ((AccountAuthentication) SecurityContextHolder.getContext().getAuthentication()).getAccountId();
        log.info("Request to logout account received. Account id = {}", accountId);
        authenticationCommunicationService.logout(accountId);
        log.info("Response on logout account. Account id = {}", accountId);
    }

}
