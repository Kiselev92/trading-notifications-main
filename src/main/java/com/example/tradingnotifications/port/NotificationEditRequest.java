package com.example.tradingnotifications.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEditRequest {

    /**
     * Идентификатор уведомления.
     */
    private Long id;

    /**
     * Идентификатор актива
     */
    private Long stockId;

    /**
     * Значение срабатывания уведомления
     */
    private BigDecimal targetValue;

    /**
     * Комментарий
     */
    private String comment;
}
