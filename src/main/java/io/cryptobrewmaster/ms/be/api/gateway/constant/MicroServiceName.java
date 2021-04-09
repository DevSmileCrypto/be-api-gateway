package io.cryptobrewmaster.ms.be.api.gateway.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MicroServiceName {

    BE_AUTHENTICATION("be-authentication");

    private final String providerName;

}
