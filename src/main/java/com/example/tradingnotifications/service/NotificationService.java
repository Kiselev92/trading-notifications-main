package com.example.tradingnotifications.service;

import com.example.tradingnotifications.adapter.dao.NotificationDao;
import com.example.tradingnotifications.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationDao notificationDao;

    public Long create(Notification notification) {
        return notificationDao.create(notification);
    }

    public Notification findById(Long id) { return notificationDao.findById(id); }

    public void deleteById(Long id) { notificationDao.deleteById(id); }
}