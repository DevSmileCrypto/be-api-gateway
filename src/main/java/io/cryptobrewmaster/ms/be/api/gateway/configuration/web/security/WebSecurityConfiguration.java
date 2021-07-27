package io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service.AuthenticationCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.authentication.AuthenticationFilter;
import io.cryptobrewmaster.ms.be.library.configuration.web.filter.MDCFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationCommunicationService authenticationCommunicationService;
    private final ObjectMapper mapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/actuator/health",
                        "/api/authentication/login/**",
                        "/api/authentication/refresh/**",
                        "/api/websocket"
                ).permitAll()
                .antMatchers("/actuator/refresh").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(new AuthenticationFilter(authenticationCommunicationService, mapper), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new MDCFilter(), AuthenticationFilter.class);
    }

}
