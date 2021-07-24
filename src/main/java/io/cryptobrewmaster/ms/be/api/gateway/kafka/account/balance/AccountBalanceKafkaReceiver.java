package io.cryptobrewmaster.ms.be.api.gateway.kafka.account.balance;

import io.cryptobrewmaster.ms.be.api.gateway.web.model.account.balance.AccountBalanceDto;
import io.cryptobrewmaster.ms.be.library.constants.EntityStatus;
import io.cryptobrewmaster.ms.be.library.dto.EntityState;
import io.cryptobrewmaster.ms.be.library.kafka.dto.account.balance.KafkaAccountBalance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountBalanceKafkaReceiver {

    private final SimpMessagingTemplate messagingTemplate;

    public void outcome(KafkaAccountBalance kafkaAccountBalance, Headers headers) {
        var header = headers.lastHeader(EntityStatus.KAFKA_HEADER);

        var state = new EntityState<>(
                EntityStatus.valueOf(header.value()),
                AccountBalanceDto.of(kafkaAccountBalance)
        );

        messagingTemplate.convertAndSendToUser(
                kafkaAccountBalance.getAccountId(), "/topic/balance",
                state
        );
    }

}
