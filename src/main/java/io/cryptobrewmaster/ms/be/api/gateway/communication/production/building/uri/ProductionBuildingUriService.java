package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.uri;

import java.net.URI;

/**
 * The interface Production building uri service.
 */
public interface ProductionBuildingUriService {

    /**
     * Gets all water pump building craft history for ui by account id uri.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all water pump building craft history for ui by account id uri
     */
    URI getAllWaterPumpBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size);

    /**
     * Gets water pump building rent uri.
     *
     * @param accountId the account id
     * @return the water pump building rent uri
     */
    URI getWaterPumpBuildingRentUri(String accountId);

    /**
     * Gets water pump building restore condition uri.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     * @return the water pump building restore condition uri
     */
    URI getWaterPumpBuildingRestoreConditionUri(String accountId, Long buildingId);

    /**
     * Gets water pump building craft uri.
     *
     * @param accountId the account id
     * @return the water pump building craft uri
     */
    URI getWaterPumpBuildingCraftUri(String accountId);

    /**
     * Gets water pump building craft claim uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the water pump building craft claim uri
     */
    URI getWaterPumpBuildingCraftClaimUri(String accountId, Long buildingCraftId);

    /**
     * Gets water pump building craft completion uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the water pump building craft completion uri
     */
    URI getWaterPumpBuildingCraftCompletionUri(String accountId, Long buildingCraftId);

    /**
     * Gets all grain field building craft history for ui by account id uri.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all grain field building craft history for ui by account id uri
     */
    URI getAllGrainFieldBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size);

    /**
     * Gets grain field building rent uri.
     *
     * @param accountId the account id
     * @return the grain field building rent uri
     */
    URI getGrainFieldBuildingRentUri(String accountId);

    /**
     * Gets grain field building restore condition uri.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     * @return the grain field building restore condition uri
     */
    URI getGrainFieldBuildingRestoreConditionUri(String accountId, Long buildingId);

    /**
     * Gets grain field building craft uri.
     *
     * @param accountId the account id
     * @return the grain field building craft uri
     */
    URI getGrainFieldBuildingCraftUri(String accountId);

    /**
     * Gets grain field building craft claim uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the grain field building craft claim uri
     */
    URI getGrainFieldBuildingCraftClaimUri(String accountId, Long buildingCraftId);

    /**
     * Gets grain field building craft completion uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the grain field building craft completion uri
     */
    URI getGrainFieldBuildingCraftCompletionUri(String accountId, Long buildingCraftId);

    /**
     * Gets all hops field building craft history for ui by account id uri.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all hops field building craft history for ui by account id uri
     */
    URI getAllHopsFieldBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size);

    /**
     * Gets hops field building rent uri.
     *
     * @param accountId the account id
     * @return the hops field building rent uri
     */
    URI getHopsFieldBuildingRentUri(String accountId);

    /**
     * Gets hops field building restore condition uri.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     * @return the hops field building restore condition uri
     */
    URI getHopsFieldBuildingRestoreConditionUri(String accountId, Long buildingId);

    /**
     * Gets hops field building craft uri.
     *
     * @param accountId the account id
     * @return the hops field building craft uri
     */
    URI getHopsFieldBuildingCraftUri(String accountId);

    /**
     * Gets hops field building craft claim uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the hops field building craft claim uri
     */
    URI getHopsFieldBuildingCraftClaimUri(String accountId, Long buildingCraftId);

    /**
     * Gets hops field building craft completion uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the hops field building craft completion uri
     */
    URI getHopsFieldBuildingCraftCompletionUri(String accountId, Long buildingCraftId);

    /**
     * Gets all academy yeast lab building craft history for ui by account id uri.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all academy yeast lab building craft history for ui by account id uri
     */
    URI getAllAcademyYeastLabBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size);

    /**
     * Gets academy yeast lab building rent uri.
     *
     * @param accountId the account id
     * @return the academy yeast lab building rent uri
     */
    URI getAcademyYeastLabBuildingRentUri(String accountId);

    /**
     * Gets academy yeast lab building restore condition uri.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     * @return the academy yeast lab building restore condition uri
     */
    URI getAcademyYeastLabBuildingRestoreConditionUri(String accountId, Long buildingId);

    /**
     * Gets academy yeast lab building craft uri.
     *
     * @param accountId the account id
     * @return the academy yeast lab building craft uri
     */
    URI getAcademyYeastLabBuildingCraftUri(String accountId);

    /**
     * Gets academy yeast lab building craft claim uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the academy yeast lab building craft claim uri
     */
    URI getAcademyYeastLabBuildingCraftClaimUri(String accountId, Long buildingCraftId);

    /**
     * Gets academy yeast lab building craft completion uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the academy yeast lab building craft completion uri
     */
    URI getAcademyYeastLabBuildingCraftCompletionUri(String accountId, Long buildingCraftId);

    /**
     * Gets all malt house building craft history for ui by account id uri.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all malt house building craft history for ui by account id uri
     */
    URI getAllMaltHouseBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size);

    /**
     * Gets malt house building rent uri.
     *
     * @param accountId the account id
     * @return the malt house building rent uri
     */
    URI getMaltHouseBuildingRentUri(String accountId);

    /**
     * Gets malt house building restore condition uri.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     * @return the malt house building restore condition uri
     */
    URI getMaltHouseBuildingRestoreConditionUri(String accountId, Long buildingId);

    /**
     * Gets malt house building craft uri.
     *
     * @param accountId the account id
     * @return the malt house building craft uri
     */
    URI getMaltHouseBuildingCraftUri(String accountId);

    /**
     * Gets malt house building craft claim uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the malt house building craft claim uri
     */
    URI getMaltHouseBuildingCraftClaimUri(String accountId, Long buildingCraftId);

    /**
     * Gets malt house building craft completion uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the malt house building craft completion uri
     */
    URI getMaltHouseBuildingCraftCompletionUri(String accountId, Long buildingCraftId);

    /**
     * Gets all brew house building craft history for ui by account id uri.
     *
     * @param accountId the account id
     * @param page      the page
     * @param size      the size
     * @return the all brew house building craft history for ui by account id uri
     */
    URI getAllBrewHouseBuildingCraftHistoryForUiByAccountIdUri(String accountId, Integer page, Integer size);

    /**
     * Gets brew house building restore condition uri.
     *
     * @param accountId  the account id
     * @param buildingId the building id
     * @return the brew house building restore condition uri
     */
    URI getBrewHouseBuildingRestoreConditionUri(String accountId, Long buildingId);

    /**
     * Gets brew house building craft uri.
     *
     * @param accountId the account id
     * @return the brew house building craft uri
     */
    URI getBrewHouseBuildingCraftUri(String accountId);

    /**
     * Gets brew house building craft claim uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the brew house building craft claim uri
     */
    URI getBrewHouseBuildingCraftClaimUri(String accountId, Long buildingCraftId);

    /**
     * Gets brew house building craft completion uri.
     *
     * @param accountId       the account id
     * @param buildingCraftId the building craft id
     * @return the brew house building craft completion uri
     */
    URI getBrewHouseBuildingCraftCompletionUri(String accountId, Long buildingCraftId);

    /**
     * Gets account building state for ui by account id uri.
     *
     * @param accountId the account id
     * @return the account building state for ui by account id uri
     */
    URI getAccountBuildingStateForUiByAccountIdUri(String accountId);

}
