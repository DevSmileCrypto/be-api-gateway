package io.cryptobrewmaster.ms.be.api.gateway.configuration.kafka;

import io.cryptobrewmaster.ms.be.api.gateway.configuration.kafka.properties.KafkaProperties;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingCraftChancesRequest;
import io.cryptobrewmaster.ms.be.library.kafka.dto.building.KafkaBuildingStateRequest;
import io.cryptobrewmaster.ms.be.library.kafka.interceptor.KafkaProducerInterceptor;
import io.cryptobrewmaster.ms.be.library.kafka.interceptor.building.KafkaBuildingCraftChancesRequestProducerInterceptor;
import io.cryptobrewmaster.ms.be.library.kafka.interceptor.building.KafkaBuildingStateRequestProducerInterceptor;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.KafkaBuildingCraftChancesRequestSerde;
import io.cryptobrewmaster.ms.be.library.kafka.serde.building.KafkaBuildingStateRequestSerde;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfiguration {

    private final KafkaProperties kafkaProperties;

    private <V> Map<String, Object> producerConfigs(Class<? extends Serializer<V>> valueSerializer,
                                                    Class<? extends KafkaProducerInterceptor<V>> interceptorClass) {

        var config = new HashMap<String, Object>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptorClass.getName());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        return config;
    }

    private <V> KafkaTemplate<String, V> getKafkaTemplate(Class<? extends Serializer<V>> valueSerializer,
                                                          Class<? extends KafkaProducerInterceptor<V>> interceptorClass) {

        var producerConfigs = producerConfigs(valueSerializer, interceptorClass);
        var kafkaProducerFactory = new DefaultKafkaProducerFactory<String, V>(producerConfigs);
        return new KafkaTemplate<>(kafkaProducerFactory);
    }

    @Bean
    public KafkaTemplate<String, KafkaBuildingStateRequest> buildingStateRequestKafkaTemplate() {
        return getKafkaTemplate(KafkaBuildingStateRequestSerde.class, KafkaBuildingStateRequestProducerInterceptor.class);
    }

    @Bean
    public KafkaTemplate<String, KafkaBuildingCraftChancesRequest> buildingCraftChancesRequestKafkaTemplate() {
        return getKafkaTemplate(KafkaBuildingCraftChancesRequestSerde.class, KafkaBuildingCraftChancesRequestProducerInterceptor.class);
    }

}
