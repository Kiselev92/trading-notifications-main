package com.example.tradingnotifications.domain;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@With
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class Notification {

    Long id;
    Long stockId;
    BigDecimal targetValue;
    String comment;
    Instant created;
    Instant updated;
}