package io.cryptobrewmaster.ms.be.api.gateway.communication.state.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ms.state")
public class StateProperties {

    private String uri;
    private Path path;
    private Timeout timeout;

    @Getter
    @Setter
    public static class Path {
        private String refreshServerProperties;
    }

    @Getter
    @Setter
    public static class Timeout {
        private Integer connect;
        private Integer read;
    }

}
