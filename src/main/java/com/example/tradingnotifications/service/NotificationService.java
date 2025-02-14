package com.example.tradingnotifications.service;

import com.example.tradingnotifications.adapter.dao.JdbcNotificationDao;
import com.example.tradingnotifications.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JdbcNotificationDao JdbcNotificationDao;

    public Long create(Notification notification) {
        return JdbcNotificationDao.create(notification);
    }

    public Notification findById(Long id) { return JdbcNotificationDao.findById(id); }
}