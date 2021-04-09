package io.cryptobrewmaster.ms.be.api.gateway.integration.authentication.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ms.authentication")
public class AuthenticationProperties {

    private String uri;
    private Path path;
    private Timeout timeout;

    @Getter
    @Setter
    public static class Path {
        private String logout;
        private Token token;
        private HiveKeychain hiveKeychain;
        private HiveSigner hiveSigner;

        @Getter
        @Setter
        public static class Token {
            private String validate;
            private String refresh;
        }

        @Getter
        @Setter
        public static class HiveKeychain {
            private String login;
        }

        @Getter
        @Setter
        public static class HiveSigner {
            private String login;
            private String loginRedirect;
        }
    }

    @Getter
    @Setter
    public static class Timeout {
        private Integer connect;
        private Integer read;
    }

}
