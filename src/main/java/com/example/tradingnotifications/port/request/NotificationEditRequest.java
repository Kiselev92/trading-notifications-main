package com.example.tradingnotifications.port.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEditRequest {

    @Schema(description = "Идентификатор уведомления")
    private Long id;

    @Schema(description = "Идентификатор инструмента")
    private Long stockId;

    @Schema(description = "Значение срабатывания уведомления")
    private BigDecimal targetValue;

    @Schema(description = "Комментарий")
    private String comment;
}
