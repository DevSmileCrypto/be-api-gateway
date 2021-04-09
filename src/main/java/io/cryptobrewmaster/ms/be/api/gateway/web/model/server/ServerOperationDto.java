package io.cryptobrewmaster.ms.be.api.gateway.web.model.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cryptobrewmaster.ms.be.library.constants.MicroServiceName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerOperationDto {
    @NotNull
    private MicroServiceName microServiceName;
    @NotNull
    private boolean success;
}
