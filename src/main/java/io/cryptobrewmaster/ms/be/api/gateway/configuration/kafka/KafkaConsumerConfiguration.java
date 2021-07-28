package io.cryptobrewmaster.ms.be.api.gateway.configuration.kafka;

import io.cryptobrewmaster.ms.be.api.gateway.configuration.kafka.properties.KafkaProperties;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.KafkaAccountBalance;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.beer.KafkaAccountBeerCard;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.resource.KafkaAccountResourceCard;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.KafkaAccountEnergy;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingCraftChances;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingState;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.KafkaAcademyYeastLabBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.craft.KafkaAcademyYeastLabBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.academy.lab.yeast.rent.KafkaAcademyYeastLabBuildingRent;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.KafkaGrainFieldBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.craft.KafkaGrainFieldBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.grain.rent.KafkaGrainFieldBuildingRent;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.KafkaHopsFieldBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.craft.KafkaHopsFieldBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.field.hops.rent.KafkaHopsFieldBuildingRent;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.brew.KafkaBrewHouseBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.brew.craft.KafkaBrewHouseBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.KafkaMaltHouseBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.craft.KafkaMaltHouseBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.house.malt.rent.KafkaMaltHouseBuildingRent;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.KafkaWaterPumpBuilding;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.craft.KafkaWaterPumpBuildingCraft;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.water.pump.rent.KafkaWaterPumpBuildingRent;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.balance.KafkaAccountBalanceSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.card.beer.KafkaAccountBeerCardSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.card.resource.KafkaAccountResourceCardSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.energy.KafkaAccountEnergySerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.KafkaBuildingCraftChancesSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.KafkaBuildingStateSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.academy.lab.yeast.KafkaAcademyYeastLabBuildingSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.academy.lab.yeast.craft.KafkaAcademyYeastLabBuildingCraftSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.academy.lab.yeast.rent.KafkaAcademyYeastLabBuildingRentSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.field.grain.KafkaGrainFieldBuildingSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.field.grain.craft.KafkaGrainFieldBuildingCraftSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.field.grain.rent.KafkaGrainFieldBuildingRentSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.field.hops.KafkaHopsFieldBuildingSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.field.hops.craft.KafkaHopsFieldBuildingCraftSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.field.hops.rent.KafkaHopsFieldBuildingRentSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.house.brew.KafkaBrewHouseBuildingSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.house.brew.craft.KafkaBrewHouseBuildingCraftSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.house.malt.KafkaMaltHouseBuildingSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.house.malt.craft.KafkaMaltHouseBuildingCraftSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.house.malt.rent.KafkaMaltHouseBuildingRentSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.water.pump.KafkaWaterPumpBuildingSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.water.pump.craft.KafkaWaterPumpBuildingCraftSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.water.pump.rent.KafkaWaterPumpBuildingRentSerde;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

    private final KafkaProperties kafkaProperties;

    private <V> Map<String, Object> defaultConsumerConfigs(Class<? extends Deserializer<V>> valueDeserializer) {
        var config = new HashMap<String, Object>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConfig().getGroupId());
        config.put(ConsumerConfig.CLIENT_ID_CONFIG, kafkaProperties.getConfig().getClientId());
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaProperties.getConfig().getEnableAutoCommit());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        return config;
    }

    private <V> ConcurrentKafkaListenerContainerFactory<String, V> getKafkaListenerContainerFactory(Class<? extends Deserializer<V>> valueDeserializer) {
        var defaultConfigs = defaultConsumerConfigs(valueDeserializer);
        var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(defaultConfigs);

        var factory = new ConcurrentKafkaListenerContainerFactory<String, V>();
        factory.setConsumerFactory(kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaAccountBalance> accountBalanceConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaAccountBalanceSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaAccountEnergy> accountEnergyConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaAccountEnergySerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaAccountResourceCard> accountResourceCardConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaAccountResourceCardSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaAccountBeerCard> accountBeerCardConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaAccountBeerCardSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaWaterPumpBuilding> waterPumpBuildingConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaWaterPumpBuildingSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaWaterPumpBuildingRent> waterPumpBuildingRentConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaWaterPumpBuildingRentSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaWaterPumpBuildingCraft> waterPumpBuildingCraftConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaWaterPumpBuildingCraftSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaGrainFieldBuilding> grainFieldBuildingConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaGrainFieldBuildingSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaGrainFieldBuildingRent> grainFieldBuildingRentConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaGrainFieldBuildingRentSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaGrainFieldBuildingCraft> grainFieldBuildingCraftConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaGrainFieldBuildingCraftSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaHopsFieldBuilding> hopsFieldBuildingConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaHopsFieldBuildingSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaHopsFieldBuildingRent> hopsFieldBuildingRentConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaHopsFieldBuildingRentSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaHopsFieldBuildingCraft> hopsFieldBuildingCraftConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaHopsFieldBuildingCraftSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaAcademyYeastLabBuilding> academyYeastLabBuildingConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaAcademyYeastLabBuildingSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaAcademyYeastLabBuildingRent> academyYeastLabBuildingRentConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaAcademyYeastLabBuildingRentSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaAcademyYeastLabBuildingCraft> academyYeastLabBuildingCraftConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaAcademyYeastLabBuildingCraftSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMaltHouseBuilding> maltHouseBuildingConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaMaltHouseBuildingSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMaltHouseBuildingRent> maltHouseBuildingRentConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaMaltHouseBuildingRentSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMaltHouseBuildingCraft> maltHouseBuildingCraftConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaMaltHouseBuildingCraftSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaBrewHouseBuilding> brewHouseBuildingConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaBrewHouseBuildingSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaBrewHouseBuildingCraft> brewHouseBuildingCraftConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaBrewHouseBuildingCraftSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaBuildingState> buildingStateConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaBuildingStateSerde.class);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaBuildingCraftChances> buildingCraftChancesConcurrentKafkaListenerContainerFactory() {
        return getKafkaListenerContainerFactory(KafkaBuildingCraftChancesSerde.class);
    }

}
