package io.cryptobrewmaster.ms.be.api.gateway.exception.websocket;

import io.cryptobrewmaster.ms.be.library.exception.InnerServiceException;
import org.springframework.messaging.Message;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.file.AccessDeniedException;

import static io.cryptobrewmaster.ms.be.library.exception.constants.ExceptionCode.BAD_CREDENTIALS_EXCEPTION;
import static io.cryptobrewmaster.ms.be.library.exception.constants.ExceptionCode.UNKNOWN_EXCEPTION;

public class WebSocketErrorHandler extends StompSubProtocolErrorHandler {

    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {
        if (ex.getCause() instanceof AccessDeniedException) {
            return super.handleClientMessageProcessingError(clientMessage, new BadCredentialsException(BAD_CREDENTIALS_EXCEPTION.getDebugMessage()));
        }
        return super.handleClientMessageProcessingError(clientMessage, new InnerServiceException(UNKNOWN_EXCEPTION.getDebugMessage()));
    }

}
