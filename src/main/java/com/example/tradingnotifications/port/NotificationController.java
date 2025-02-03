package com.example.tradingnotifications.port;

import com.example.tradingnotifications.domain.Notification;
import com.example.tradingnotifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Создать уведомление
     *
     * @param request - поля уведомления (тело POST запроса)
     * @return идентификатор уведомления
     */
    @PostMapping("/notification")
    public Long create(@RequestHeader(name = "OS") String os,
                       @RequestBody NotificationCreateRequest request) {
        return notificationService.create(toModel(request));
    }

    private static Notification toModel(NotificationCreateRequest request) {
        Instant now = Instant.now();
        return Notification.builder()
                .stockId(request.getStockId())
                .targetValue(request.getTargetValue())
                .comment(request.getComment())
                .created(now)
                .updated(now)
                .build();
    }
}
