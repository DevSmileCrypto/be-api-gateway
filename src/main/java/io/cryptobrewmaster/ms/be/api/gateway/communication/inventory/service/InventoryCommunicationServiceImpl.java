package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardStateUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.AccountCardUiFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.uri.InventoryUriService;
import io.cryptobrewmaster.ms.be.library.communication.BaseCommunicationService;
import io.cryptobrewmaster.ms.be.library.communication.model.RequestLog;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class InventoryCommunicationServiceImpl extends BaseCommunicationService implements InventoryCommunicationService {

    private final InventoryUriService inventoryUriService;

    public InventoryCommunicationServiceImpl(RestTemplate inventoryRestTemplate,
                                             InventoryUriService inventoryUriService) {
        super(inventoryRestTemplate);
        this.inventoryUriService = inventoryUriService;
    }

    String getMicroServiceName() {
        return MicroServiceName.BE_INVENTORY.getProviderName();
    }

    @Override
    public AccountCardStateUiDto getAccountCardStateForUi(AccountCardUiFetchedCriteriaDto criteriaDto) {
        return performRequestWithResponse(
                inventoryUriService.getAccountCardStateFetchForUiUri(),
                HttpMethod.POST, criteriaDto, AccountCardStateUiDto.class,
                new RequestLog(
                        "Request to get account card state for ui by criteria send to %s ms. Criteria = %s",
                        List.of(getMicroServiceName(), criteriaDto),
                        "Response on get account card state for ui by criteria from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on get account card state for ui by criteria request. Criteria = %s.",
                        List.of(getMicroServiceName(), criteriaDto)
                )
        );
    }
}
