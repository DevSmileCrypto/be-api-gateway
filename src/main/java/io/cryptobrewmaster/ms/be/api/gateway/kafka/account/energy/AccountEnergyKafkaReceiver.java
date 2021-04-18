package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.energy;

import io.cryptobrewmaster.ms.be.api.gateway.web.model.account.energy.AccountEnergyDto;
import io.cryptobrewmaster.ms.be.library.constants.EntityStatus;
import io.cryptobrewmaster.ms.be.library.dto.EntityState;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.energy.AccountEnergyKDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountEnergyKafkaReceiver {

    private final SimpMessagingTemplate messagingTemplate;

    public void output(AccountEnergyKDto accountEnergyKDto, Headers headers) {
        Header header = headers.lastHeader(EntityStatus.KAFKA_HEADER);

        var state = new EntityState<>(
                EntityStatus.valueOf(header.value()),
                AccountEnergyDto.of(accountEnergyKDto)
        );

        messagingTemplate.convertAndSendToUser(
                accountEnergyKDto.getAccountId(), "/topic/energy",
                state
        );
    }

}
