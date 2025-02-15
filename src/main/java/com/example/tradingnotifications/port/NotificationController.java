package com.example.tradingnotifications.port;

import com.example.tradingnotifications.domain.Notification;
import com.example.tradingnotifications.port.request.NotificationCreateRequest;
import com.example.tradingnotifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    /**
     * Получить уведомление по id
     * @return id уведомления
     */
    @GetMapping("/notification/{id}")
    public Notification getNotification(@PathVariable("id") Long id) {
        return notificationService.findById(id);
    }
}