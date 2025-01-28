package com.example.tradingnotifications.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Notification {

    Long id;
    Long stockId;
    BigDecimal targetValue;
    String comment;
    Instant created;
    Instant updated;

    public static Long UNASSIGNED_ID = -1L;

    public static Notification createNew(
            String comment, Long stockId, BigDecimal targetValue
    ) {
        return Notification.builder()
                .id(UNASSIGNED_ID)
                .stockId(stockId)
                .targetValue(targetValue)
                .comment(comment)
                .build();
    }
}
