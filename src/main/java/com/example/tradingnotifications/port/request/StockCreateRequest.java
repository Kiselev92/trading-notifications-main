package com.example.tradingnotifications.port.request;

import com.example.tradingnotifications.domain.StockType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCreateRequest {
    
    private StockType type;

    private String ticker;

    private String name;
}
