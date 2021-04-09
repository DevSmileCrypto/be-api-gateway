package io.cryptobrewmaster.ms.be.api.gateway.service;

import io.cryptobrewmaster.ms.be.api.gateway.web.model.server.ServerOperationDto;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;

import java.util.List;

/**
 * The interface Server service.
 */
public interface ServerService {

    /**
     * Refresh properties list.
     *
     * @param microServiceNames the micro service names
     * @return the list
     */
    List<ServerOperationDto> refreshServersProperties(List<MicroServiceName> microServiceNames);

}
