package io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.service;

import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.AccountBeerCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.beer.criteria.AccountBeerCardUiFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.AccountResourceCardUiDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.dto.resource.criteria.AccountResourceCardUiFetchedCriteriaDto;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.uri.InventoryUriService;
import io.cryptobrewmaster.ms.be.library.communication.BaseCommunicationService;
import io.cryptobrewmaster.ms.be.library.communication.model.RequestLog;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import org.springframework.core.ParameterizedTypeReference;
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
    public List<AccountResourceCardUiDto> fetchAllAccountResourceCardForUi(AccountResourceCardUiFetchedCriteriaDto criteriaDto) {
        return performRequestWithResponse(
                inventoryUriService.getAccountResourceCardFetchForUiUri(),
                HttpMethod.POST, criteriaDto, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to fetch all account resource card for ui by criteria send to %s ms. Criteria = %s",
                        List.of(getMicroServiceName(), criteriaDto),
                        "Response on fetch all account resource card for ui by criteria from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on fetch all account resource card for ui by criteria request. Criteria = %s.",
                        List.of(getMicroServiceName(), criteriaDto)
                )
        );
    }

    @Override
    public List<AccountBeerCardUiDto> fetchAllAccountBeerCardForUi(AccountBeerCardUiFetchedCriteriaDto criteriaDto) {
        return performRequestWithResponse(
                inventoryUriService.getAccountBeerCardFetchForUiUri(),
                HttpMethod.POST, criteriaDto, new ParameterizedTypeReference<>() {
                },
                new RequestLog(
                        "Request to fetch all account beer card for ui by criteria send to %s ms. Criteria = %s",
                        List.of(getMicroServiceName(), criteriaDto),
                        "Response on fetch all account beer card for ui by criteria from %s ms.",
                        List.of(getMicroServiceName()),
                        "No response from %s ms on fetch all account beer card for ui by criteria request. Criteria = %s.",
                        List.of(getMicroServiceName(), criteriaDto)
                )
        );
    }

}
