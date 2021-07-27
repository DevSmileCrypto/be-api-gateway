package io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service.AuthenticationCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.web.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.library.exception.BaseException;
import io.cryptobrewmaster.ms.be.library.exception.dto.ErrorInfoDto;
import io.cryptobrewmaster.ms.be.library.exception.integration.RemoteMsPassThroughException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static io.cryptobrewmaster.ms.be.library.exception.util.ControllerExceptionLogger.log;


@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationCommunicationService authenticationCommunicationService;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = resolveToken(request);
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.isNull(authentication) && StringUtils.isNotBlank(token)) {
                var accountAuthenticationDto = authenticationCommunicationService.validateAccessToken(token);
                if (accountAuthenticationDto.isValid()) {
                    var accountAuthentication = new AccountAuthentication(accountAuthenticationDto);
                    SecurityContextHolder.getContext().setAuthentication(accountAuthentication);
                }
            }
        } catch (BaseException e) {
            SecurityContextHolder.clearContext();
            log(request, e);
            returnErrorInResponse(response, ErrorInfoDto.of(e));
            return;
        } catch (RemoteMsPassThroughException e) {
            SecurityContextHolder.clearContext();
            log(request, e);
            returnErrorInResponse(response, e.getErrorInfoDto());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "";
    }

    private void returnErrorInResponse(HttpServletResponse response, ErrorInfoDto errorInfoDto) throws IOException {
        response.setStatus(errorInfoDto.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ServletOutputStream outputStream = response.getOutputStream();
        mapper.writeValue(outputStream, errorInfoDto);
        outputStream.flush();
    }

}
