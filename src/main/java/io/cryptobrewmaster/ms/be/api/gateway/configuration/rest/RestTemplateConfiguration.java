package io.cryptobrewmaster.ms.be.api.gateway.configuration.rest;

import io.cryptobrewmaster.ms.be.api.gateway.communication.account.properties.AccountProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.authentication.properties.AuthenticationProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.core.properties.CoreProperties;
import io.cryptobrewmaster.ms.be.api.gateway.communication.state.properties.StateProperties;
import io.cryptobrewmaster.ms.be.api.gateway.configuration.rest.properties.RestTemplateProperties;
import io.cryptobrewmaster.ms.be.library.configuration.rest.interceptor.JsonContentTypeRestTemplateInterceptor;
import io.cryptobrewmaster.ms.be.library.exception.integration.AccountErrorHandler;
import io.cryptobrewmaster.ms.be.library.exception.integration.AuthenticationErrorHandler;
import io.cryptobrewmaster.ms.be.library.exception.integration.CoreErrorHandler;
import io.cryptobrewmaster.ms.be.library.exception.integration.StateErrorHandler;
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
        return restTemplateBuilder.errorHandler(new AuthenticationErrorHandler())
                .rootUri(authenticationProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(authenticationProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(authenticationProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "accountRestTemplate")
    public RestTemplate accountRestTemplate(RestTemplateBuilder restTemplateBuilder, AccountProperties accountProperties) {
        return restTemplateBuilder.errorHandler(new AccountErrorHandler())
                .rootUri(accountProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(accountProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(accountProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "coreRestTemplate")
    public RestTemplate coreRestTemplate(RestTemplateBuilder restTemplateBuilder, CoreProperties coreProperties) {
        return restTemplateBuilder.errorHandler(new CoreErrorHandler())
                .rootUri(coreProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(coreProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(coreProperties.getTimeout().getRead()))
                .build();
    }

    @Bean(name = "stateRestTemplate")
    public RestTemplate stateRestTemplate(RestTemplateBuilder restTemplateBuilder, StateProperties stateProperties) {
        return restTemplateBuilder.errorHandler(new StateErrorHandler())
                .rootUri(stateProperties.getUri())
                .interceptors(new JsonContentTypeRestTemplateInterceptor())
                .setConnectTimeout(Duration.ofMillis(stateProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(stateProperties.getTimeout().getRead()))
                .build();
    }

}
