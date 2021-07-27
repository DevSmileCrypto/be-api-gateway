package io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.properties.AccountBalanceProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AccountBalanceUriServiceImpl implements AccountBalanceUriService {

    private final AccountBalanceProperties accountBalanceProperties;

    @Override
    public URI getAccountBalanceFetchForUiUri() {
        return UriComponentsBuilder.fromUriString(accountBalanceProperties.getUri())
                .path(accountBalanceProperties.getPath().getAccountBalanceFetchForUi())
                .build()
                .encode()
                .toUri();
    }

}
