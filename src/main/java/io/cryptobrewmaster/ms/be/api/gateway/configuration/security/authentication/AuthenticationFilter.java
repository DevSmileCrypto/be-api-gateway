package io.cryptobrewmaster.ms.be.api.gateway.configuration.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.dto.AccountAuthenticationDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.service.AuthenticationCommunicationService;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.security.model.AccountAuthentication;
import io.cryptobrewmaster.ms.be.library.exception.BaseException;
import io.cryptobrewmaster.ms.be.library.exception.dto.ErrorInfoDto;
import io.cryptobrewmaster.ms.be.library.exception.integration.RemoteMsPassThroughException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static io.cryptobrewmaster.ms.be.api.gateway.exception.util.ControllerExceptionUtil.log;

@RequiredArgsConstructor
public class AuthenticationFilter extends GenericFilterBean {

    private final AuthenticationCommunicationService authenticationCommunicationService;
    private final ObjectMapper mapper;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;

        try {
            String token = resolveToken(httpRequest);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.isNull(authentication) && StringUtils.isNotBlank(token)) {
                AccountAuthenticationDto accountAuthenticationDto = authenticationCommunicationService.validateAccessToken(token);
                if (accountAuthenticationDto.isValid()) {
                    AccountAuthentication accountAuthentication = new AccountAuthentication(accountAuthenticationDto);
                    SecurityContextHolder.getContext().setAuthentication(accountAuthentication);
                }
            }
        } catch (BaseException e) {
            SecurityContextHolder.clearContext();
            log(httpRequest, e);
            returnErrorInResponse(httpResponse, ErrorInfoDto.of(e));
            return;
        } catch (RemoteMsPassThroughException e) {
            SecurityContextHolder.clearContext();
            log(httpRequest, e);
            returnErrorInResponse(httpResponse, e.getErrorInfoDto());
            return;
        }

        filterChain.doFilter(req, res);
    }

    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer " )) {
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
