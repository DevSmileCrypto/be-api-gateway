package io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ms.production-building")
public class ProductionBuildingProperties {

    private String uri;
    private Path path;
    private Timeout timeout;

    @Getter
    @Setter
    public static class Path {
        private String waterPumpBuildingForUi;
        private String waterPumpBuildingCraftHistoryForUi;
        private String waterPumpBuildingRent;
        private String waterPumpBuildingRestoreCondition;
        private String waterPumpBuildingCraft;
        private String waterPumpBuildingCraftClaim;
        private String waterPumpBuildingCraftCompletion;

        private String grainFieldBuildingForUi;
        private String grainFieldBuildingCraftHistoryForUi;
        private String grainFieldBuildingRent;
        private String grainFieldBuildingRestoreCondition;
        private String grainFieldBuildingCraft;
        private String grainFieldBuildingCraftClaim;
        private String grainFieldBuildingCraftCompletion;

        private String hopsFieldBuildingForUi;
        private String hopsFieldBuildingCraftHistoryForUi;
        private String hopsFieldBuildingRent;
        private String hopsFieldBuildingRestoreCondition;
        private String hopsFieldBuildingCraft;
        private String hopsFieldBuildingCraftClaim;
        private String hopsFieldBuildingCraftCompletion;

        private String academyYeastLabBuildingForUi;
        private String academyYeastLabBuildingCraftHistoryForUi;
        private String academyYeastLabBuildingRent;
        private String academyYeastLabBuildingRestoreCondition;
        private String academyYeastLabBuildingCraft;
        private String academyYeastLabBuildingCraftClaim;
        private String academyYeastLabBuildingCraftCompletion;

        private String maltHouseBuildingForUi;
        private String maltHouseBuildingCraftHistoryForUi;
        private String maltHouseBuildingRent;
        private String maltHouseBuildingRestoreCondition;
        private String maltHouseBuildingCraft;
        private String maltHouseBuildingCraftClaim;
        private String maltHouseBuildingCraftCompletion;

        private String brewHouseBuildingForUi;
        private String brewHouseBuildingCraftHistoryForUi;
        private String brewHouseBuildingRestoreCondition;
        private String brewHouseBuildingCraft;
        private String brewHouseBuildingCraftClaim;
        private String brewHouseBuildingCraftCompletion;
    }

    @Getter
    @Setter
    public static class Timeout {
        private Integer connect;
        private Integer read;
    }

}
