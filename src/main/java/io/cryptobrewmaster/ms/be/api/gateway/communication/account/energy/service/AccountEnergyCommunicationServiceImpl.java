package io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.AccountEnergyUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.dto.criteria.AccountEnergyFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.uri.AccountEnergyUriService;
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
public class AccountEnergyCommunicationServiceImpl extends BaseCommunicationService implements AccountEnergyCommunicationService {

    private final AccountEnergyUriService accountEnergyUriService;

    public AccountEnergyCommunicationServiceImpl(RestTemplate accountEnergyRestTemplate,
                                                 AccountEnergyUriService accountEnergyUriService) {
        super(accountEnergyRestTemplate);
        this.accountEnergyUriService = accountEnergyUriService;
    }

    String getMicroServiceName() {
        return MicroServiceName.BE_ACCOUNT_ENERGY.getProviderName();
    }

    @Override
    public PageDto<AccountEnergyUiDto> fetchAllAccountEnergyForUi(AccountEnergyFetchedCriteriaDto criteriaDto) {
        return performRequestWithResponse(
                accountEnergyUriService.getAccountEnergyFetchForUiUri(),
                HttpMethod.POST, criteriaDto, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to fetch all account energy for ui by criteria send to %s ms. Criteria = %s",
                        List.of(getMicroServiceName(), criteriaDto),
                        "Response on fetch all account energy for ui by criteria from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on fetch all account energy for ui by criteria request. Criteria = %s.",
                        List.of(getMicroServiceName(), criteriaDto)
                )
        );
    }

}
