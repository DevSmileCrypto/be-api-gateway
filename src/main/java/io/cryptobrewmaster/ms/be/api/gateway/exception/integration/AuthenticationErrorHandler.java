package io.cryptobrewmaster.ms.be.api.gateway.exception.integration;

import io.cryptobrewmaster.ms.be.api.gateway.constant.MicroServiceName;
import io.cryptobrewmaster.ms.be.api.gateway.exception.model.ErrorInfoDto;

public class AuthenticationErrorHandler extends BasicResponseErrorHandler {

    @Override
    String getServiceName() {
        return MicroServiceName.BE_AUTHENTICATION.getProviderName();
    }

    @Override
    void handleError(ErrorInfoDto errorInfoDto) {
        throw new RemoteMsPassThroughException(errorInfoDto);
    }

}
