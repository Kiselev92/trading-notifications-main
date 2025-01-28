package com.example.tradingnotifications.port;

import com.example.tradingnotifications.domain.Notification;
import com.example.tradingnotifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/notification")
    public Long createRandomNotification() {
        return notificationService.create(Notification.createNew(
                "test-name", 123L, new BigDecimal(1000)
        ));
    }
}
