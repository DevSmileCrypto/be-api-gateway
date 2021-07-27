package io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.AccountBalanceUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.dto.criteria.AccountBalanceFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.uri.AccountBalanceUriService;
import io.cryptobrewmaster.ms.be.library.communication.BaseCommunicationService;
import io.cryptobrewmaster.ms.be.library.communication.model.RequestLog;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import io.cryptobrewmaster.ms.be.library.dto.PageDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class AccountBalanceCommunicationServiceImpl extends BaseCommunicationService implements AccountBalanceCommunicationService {

    private final AccountBalanceUriService accountBalanceUriService;

    public AccountBalanceCommunicationServiceImpl(RestTemplate accountBalanceRestTemplate,
                                                  AccountBalanceUriService accountBalanceUriService) {
        super(accountBalanceRestTemplate);
        this.accountBalanceUriService = accountBalanceUriService;
    }

    String getMicroServiceName() {
        return MicroServiceName.BE_ACCOUNT_BALANCE.getProviderName();
    }

    @Override
    public PageDto<AccountBalanceUiDto> fetchAllAccountBalanceForUi(AccountBalanceFetchedCriteriaDto criteriaDto) {
        return performRequestWithResponse(
                accountBalanceUriService.getAccountBalanceFetchForUiUri(),
                HttpMethod.POST, criteriaDto, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to fetch all account balance for ui by criteria send to %s ms. Criteria = %s",
                        List.of(getMicroServiceName(), criteriaDto),
                        "Response on fetch all account balance for ui by criteria from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on fetch all account balance for ui by criteria request. Criteria = %s.",
                        List.of(getMicroServiceName(), criteriaDto)
                )
        );
    }

}
