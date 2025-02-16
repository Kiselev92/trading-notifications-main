package com.example.tradingnotifications.port;

import com.example.tradingnotifications.domain.Notification;
import com.example.tradingnotifications.service.NotificationService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Создать уведомление
     *
     * @param request - поля уведомления (тело POST запроса)
     * @return идентификатор уведомления
     */
    @PostMapping
    public Long create(@RequestHeader(name = "OS") String os,
                       @RequestBody NotificationCreateRequest request) {
        return notificationService.create(toModel(request));
    }

    /**
     * Получить уведомление по id
     * @return id уведомления
     */
    @GetMapping("/{id}")
    public Notification getNotification(@PathVariable("id") Long id) {
        return notificationService.findById(id);
    }

    /**
     * Изменить уведомление по id
     * @return id уведомления
     */

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable("id") Long id, @RequestBody NotificationUpdateRequest request) {
        Notification existingNotification = notificationService.findById(id);
        return notificationService.update(id, toModel(request, existingNotification));
    }

    /**
     * Удалить уведомление по id
     */
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable("id") Long id) {
        notificationService.deleteById(id);
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

    private static Notification toModel(NotificationUpdateRequest updateRequest, Notification existingNotification) {
        return Notification.builder()
                .id(existingNotification.getId())
                .stockId(updateRequest.getStockId() != null ? updateRequest.getStockId() : existingNotification.getStockId())
                .targetValue(updateRequest.getTargetValue() != null ? updateRequest.getTargetValue() : existingNotification.getTargetValue())
                .comment(updateRequest.getComment() != null ? updateRequest.getComment() : existingNotification.getComment())
                .created(existingNotification.getCreated())
                .updated(Instant.now())
                .build();
    }
}