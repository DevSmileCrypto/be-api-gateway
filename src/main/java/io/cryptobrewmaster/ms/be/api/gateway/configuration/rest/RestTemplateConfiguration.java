package io.cryptobrewmaster.ms.be.api.gateway.configuration.rest;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.balance.properties.AccountBalanceProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.energy.properties.AccountEnergyProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.account.self.properties.AccountProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.properties.AuthenticationProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.inventory.properties.InventoryProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.production.building.properties.ProductionBuildingProperties;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.rest.properties.RestTemplateProperties;
import io.cryptobrewmaster.ms.be.library.configuration.rest.interceptor.JsonContentTypeRestTemplateInterceptor;
import io.cryptobrewmaster.ms.be.library.configuration.rest.interceptor.MDCRestTemplateInterceptor;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import io.cryptobrewmaster.ms.be.library.exception.integration.CommunicationErrorHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(RestTemplateProperties restTemplateProperties) {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(restTemplateProperties.getMaxTotal());
        manager.setDefaultMaxPerRoute(restTemplateProperties.getDefaultMaxPerRoute());
        return manager;
    }

    @Bean
    public RequestConfig requestConfig(RestTemplateProperties restTemplateProperties) {
        return RequestConfig.custom()
                .setSocketTimeout(restTemplateProperties.getSocketTimeout())
                .build();
    }

    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager, RequestConfig requestConfig) {
        return HttpClientBuilder.create()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder(HttpClient httpClient) {
        return new RestTemplateBuilder()
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @Bean(name = "authenticationRestTemplate")
    public RestTemplate authenticationRestTemplate(RestTemplateBuilder restTemplateBuilder, AuthenticationProperties authenticationProperties) {
        return restTemplateBuilder.errorHandler(new CommunicationErrorHandler(MicroServiceName.BE_AUTHENTICATION))
                .rootUri(authenticationProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .additionalInterceptors(new MDCRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(authenticationProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(authenticationProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "accountRestTemplate")
    public RestTemplate accountRestTemplate(RestTemplateBuilder restTemplateBuilder, AccountProperties accountProperties) {
        return restTemplateBuilder.errorHandler(new CommunicationErrorHandler(MicroServiceName.BE_ACCOUNT))
                .rootUri(accountProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .additionalInterceptors(new MDCRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(accountProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(accountProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "accountBalanceRestTemplate")
    public RestTemplate accountBalanceRestTemplate(RestTemplateBuilder restTemplateBuilder, AccountBalanceProperties accountBalanceProperties) {
        return restTemplateBuilder.errorHandler(new CommunicationErrorHandler(MicroServiceName.BE_ACCOUNT_BALANCE))
                .rootUri(accountBalanceProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .additionalInterceptors(new MDCRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(accountBalanceProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(accountBalanceProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "accountEnergyRestTemplate")
    public RestTemplate accountEnergyRestTemplate(RestTemplateBuilder restTemplateBuilder, AccountEnergyProperties accountEnergyProperties) {
        return restTemplateBuilder.errorHandler(new CommunicationErrorHandler(MicroServiceName.BE_ACCOUNT_ENERGY))
                .rootUri(accountEnergyProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .additionalInterceptors(new MDCRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(accountEnergyProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(accountEnergyProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "inventoryRestTemplate")
    public RestTemplate inventoryRestTemplate(RestTemplateBuilder restTemplateBuilder, InventoryProperties inventoryProperties) {
        return restTemplateBuilder.errorHandler(new CommunicationErrorHandler(MicroServiceName.BE_INVENTORY))
                .rootUri(inventoryProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .additionalInterceptors(new MDCRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(inventoryProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(inventoryProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "productionBuildingRestTemplate")
    public RestTemplate productionBuildingRestTemplate(RestTemplateBuilder restTemplateBuilder, ProductionBuildingProperties productionBuildingProperties) {
        return restTemplateBuilder.errorHandler(new CommunicationErrorHandler(MicroServiceName.BE_PRODUCTION_BUILDING))
                .rootUri(productionBuildingProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .additionalInterceptors(new MDCRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(productionBuildingProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(productionBuildingProperties.getTimeout().getRead()))
                .build();
    }

}
