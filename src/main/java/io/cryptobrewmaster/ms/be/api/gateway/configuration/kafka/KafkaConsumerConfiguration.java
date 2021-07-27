package io.cryptobrewmaster.ms.be.api.gateway.configuration.kafka;

import io.cryptobrewmaster.ms.be.api.gateway.configuration.kafka.properties.KafkaProperties;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.KafkaAccountBalance;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.beer.KafkaAccountBeerCard;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.card.resource.KafkaAccountResourceCard;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.KafkaAccountEnergy;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.balance.KafkaAccountBalanceSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.card.beer.KafkaAccountBeerCardSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.card.resource.KafkaAccountResourceCardSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.account.energy.KafkaAccountEnergySerde;
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

}
