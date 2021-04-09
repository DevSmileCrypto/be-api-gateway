package io.cryptobrewmaster.ms.be.api.gateway.configuration.websocket.interceptor;

import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.dto.AccountAuthenticationDto;
import io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.service.AuthenticationCommunicationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
public class WebSocketAuthenticationChannelInterceptor implements ChannelInterceptor {

    public static final String ACCESS_TOKEN_HEADER_NAME = "Access-Token";
    private final AuthenticationCommunicationService authenticationCommunicationService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT == stompHeaderAccessor.getCommand()) {
            String accessToken = stompHeaderAccessor.getFirstNativeHeader(ACCESS_TOKEN_HEADER_NAME);
            getUser(accessToken)
                    .ifPresent(stompHeaderAccessor::setUser);
        }
        return message;
    }

    private Optional<Principal> getUser(String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            return Optional.empty();
        }
        AccountAuthenticationDto accountAuthenticationDto = authenticationCommunicationService.validateAccessToken(accessToken);
        if (!accountAuthenticationDto.isValid()) {
            return Optional.empty();
        }

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(accountAuthenticationDto.getAccountId(), null, null);
        return Optional.of(usernamePasswordAuthenticationToken);
    }

}
