package io.cryptobrewmaster.ms.be.api.gateway.configuration.websocket;

import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service.AuthenticationCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.websocket.interceptor.WebSocketAuthenticationChannelInterceptor;
import io.cryptobrewmaster.ms.be.api.gateway.exception.websocket.WebSocketErrorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@RequiredArgsConstructor
@Configuration
public class WebSocketConfigurer implements WebSocketMessageBrokerConfigurer {

    private final AuthenticationCommunicationService authenticationCommunicationService;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/account", "/topic" );
        config.setUserDestinationPrefix("/account" );
        config.setApplicationDestinationPrefixes("/app" );
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.setErrorHandler(new WebSocketErrorHandler());
        registry.addEndpoint("/api/websocket" ).setAllowedOrigins("*" );
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new WebSocketAuthenticationChannelInterceptor(authenticationCommunicationService));
    }

}
