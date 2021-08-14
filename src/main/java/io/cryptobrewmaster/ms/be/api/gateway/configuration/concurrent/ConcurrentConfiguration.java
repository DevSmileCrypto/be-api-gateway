package io.cryptobrewmaster.ms.be.api.gateway.configuration.concurrent;

import io.cryptobrewmaster.ms.be.library.service.MDCThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ConcurrentConfiguration {

    @Bean
    public ThreadPoolTaskExecutor cachedMDCThreadPoolTaskExecutor() {
        return MDCThreadPoolExecutor.getCachedInstance();
    }

}
