package com.example.tradingnotifications.port;

import com.example.tradingnotifications.domain.Notification;
import com.example.tradingnotifications.port.request.NotificationEditRequest;
import com.example.tradingnotifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Long create(@RequestBody NotificationEditRequest request) {
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
     */
    @PutMapping
    public void updateNotification(@RequestBody NotificationEditRequest request) {
        notificationService.update(toModel(request));
    }

    /**
     * Удалить уведомление по id
     */
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable("id") Long id) {
        notificationService.deleteById(id);
    }

    private static Notification toModel(NotificationEditRequest request) {
        return Notification.builder()
                .id(request.getId())
                .stockId(request.getStockId())
                .targetValue(request.getTargetValue())
                .comment(request.getComment())
                .build();
    }
}