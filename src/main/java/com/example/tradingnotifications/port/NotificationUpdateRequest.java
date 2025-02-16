package com.example.tradingnotifications.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationUpdateRequest {
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

    /**
     * Время изменения уведомления
     */
    private Instant updated;
}
