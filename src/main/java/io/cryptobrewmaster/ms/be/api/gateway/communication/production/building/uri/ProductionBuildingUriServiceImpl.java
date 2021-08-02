package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.uri;

import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.properties.ProductionBuildingProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductionBuildingUriServiceImpl implements ProductionBuildingUriService {

    private static final String ACCOUNT_ID_PARAM = "accountId";
    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";

    private final ProductionBuildingProperties inventoryProperties;

    @Override
    public URI getAllWaterPumpBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getWaterPumpBuildingCraftHistoryForUi())
                .queryParams(generateQueryParams(accountId, page, size))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getWaterPumpBuildingRentUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getWaterPumpBuildingRent())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getWaterPumpBuildingRestoreConditionUri(String accountId, Long buildingId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getWaterPumpBuildingRestoreCondition())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingId)
                .encode()
                .toUri();
    }

    @Override
    public URI getWaterPumpBuildingCraftUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getWaterPumpBuildingCraft())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getWaterPumpBuildingCraftClaimUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getWaterPumpBuildingCraftClaim())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getWaterPumpBuildingCraftCompletionUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getWaterPumpBuildingCraftCompletion())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAllGrainFieldBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getGrainFieldBuildingCraftHistoryForUi())
                .queryParams(generateQueryParams(accountId, page, size))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getGrainFieldBuildingRentUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getGrainFieldBuildingRent())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getGrainFieldBuildingRestoreConditionUri(String accountId, Long buildingId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getGrainFieldBuildingRestoreCondition())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingId)
                .encode()
                .toUri();
    }

    @Override
    public URI getGrainFieldBuildingCraftUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getGrainFieldBuildingCraft())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getGrainFieldBuildingCraftClaimUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getGrainFieldBuildingCraftClaim())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getGrainFieldBuildingCraftCompletionUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getGrainFieldBuildingCraftCompletion())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAllHopsFieldBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getHopsFieldBuildingCraftHistoryForUi())
                .queryParams(generateQueryParams(accountId, page, size))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getHopsFieldBuildingRentUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getHopsFieldBuildingRent())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getHopsFieldBuildingRestoreConditionUri(String accountId, Long buildingId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getHopsFieldBuildingRestoreCondition())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingId)
                .encode()
                .toUri();
    }

    @Override
    public URI getHopsFieldBuildingCraftUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getHopsFieldBuildingCraft())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getHopsFieldBuildingCraftClaimUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getHopsFieldBuildingCraftClaim())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getHopsFieldBuildingCraftCompletionUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getHopsFieldBuildingCraftCompletion())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAllAcademyYeastLabBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAcademyYeastLabBuildingCraftHistoryForUi())
                .queryParams(generateQueryParams(accountId, page, size))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getAcademyYeastLabBuildingRentUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAcademyYeastLabBuildingRent())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getAcademyYeastLabBuildingRestoreConditionUri(String accountId, Long buildingId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAcademyYeastLabBuildingRestoreCondition())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAcademyYeastLabBuildingCraftUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAcademyYeastLabBuildingCraft())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getAcademyYeastLabBuildingCraftClaimUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAcademyYeastLabBuildingCraftClaim())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAcademyYeastLabBuildingCraftCompletionUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAcademyYeastLabBuildingCraftCompletion())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAllMaltHouseBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getMaltHouseBuildingCraftHistoryForUi())
                .queryParams(generateQueryParams(accountId, page, size))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getMaltHouseBuildingRentUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getMaltHouseBuildingRent())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getMaltHouseBuildingRestoreConditionUri(String accountId, Long buildingId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getMaltHouseBuildingRestoreCondition())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingId)
                .encode()
                .toUri();
    }

    @Override
    public URI getMaltHouseBuildingCraftUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getMaltHouseBuildingCraft())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getMaltHouseBuildingCraftClaimUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getMaltHouseBuildingCraftClaim())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getMaltHouseBuildingCraftCompletionUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getMaltHouseBuildingCraftCompletion())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAllBrewHouseBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getBrewHouseBuildingCraftHistoryForUi())
                .queryParams(generateQueryParams(accountId, page, size))
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getBrewHouseBuildingRestoreConditionUri(String accountId, Long buildingId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getBrewHouseBuildingRestoreCondition())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingId)
                .encode()
                .toUri();
    }

    @Override
    public URI getBrewHouseBuildingCraftUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getBrewHouseBuildingCraft())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    @Override
    public URI getBrewHouseBuildingCraftClaimUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getBrewHouseBuildingCraftClaim())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getBrewHouseBuildingCraftCompletionUri(String accountId, Long buildingCraftId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getBrewHouseBuildingCraftCompletion())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .buildAndExpand(buildingCraftId)
                .encode()
                .toUri();
    }

    @Override
    public URI getAccountBuildingStateForUiByAccountIdUri(String accountId) {
        return UriComponentsBuilder.fromUriString(inventoryProperties.getUri())
                .path(inventoryProperties.getPath().getAccountBuildingStateForUi())
                .queryParam(ACCOUNT_ID_PARAM, accountId)
                .build()
                .encode()
                .toUri();
    }

    private MultiValueMap<String, String> generateQueryParams(String accountId, Integer page, Integer size) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add(ACCOUNT_ID_PARAM, accountId);

        Optional.ofNullable(page).map(String::valueOf).ifPresent(p -> queryParams.add(PAGE_PARAM, p));
        Optional.ofNullable(size).map(String::valueOf).ifPresent(s -> queryParams.add(SIZE_PARAM, s));

        return queryParams;
    }

}
