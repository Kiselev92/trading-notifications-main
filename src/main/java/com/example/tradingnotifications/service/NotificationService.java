package com.example.tradingnotifications.service;

import com.example.tradingnotifications.adapter.dao.NotificationDao;
import com.example.tradingnotifications.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationDao notificationDao;

    public Long create(Notification notification) {
        return notificationDao.create(notification);
    }

    public Notification findById(Long id) { return notificationDao.findById(id); }

    public void deleteById(Long id) { notificationDao.deleteById(id); }

    public void update(Notification newNotification) {
        Long id = newNotification.getId();
        if (id == null) {
            throw new IllegalStateException("Не указан идентификатор обновляемого уведомления. " + newNotification);
        }
        Notification oldNotification = notificationDao.findById(id);
        if (oldNotification == null) {
            throw new NoSuchElementException("Notification with id " + id + " for update not found");
        }
        notificationDao.update(id, newNotification);
    }
}