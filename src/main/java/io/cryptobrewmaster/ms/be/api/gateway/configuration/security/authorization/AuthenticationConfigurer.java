package io.cryptobrewmaster.ms.be.api.gateway.configuration.security.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.service.AuthenticationCommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthenticationCommunicationService authenticationCommunicationService;
    private final ObjectMapper mapper;

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationCommunicationService, mapper);
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
