package com.example.tradingnotifications.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@Value
@With
@Builder
@RequiredArgsConstructor
public class Stock {

    Long id;
    StockType type;
    String ticker;
    String name;
}
