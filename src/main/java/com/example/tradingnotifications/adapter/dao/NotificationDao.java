package com.example.tradingnotifications.adapter.dao;

import com.example.tradingnotifications.domain.Notification;

public interface NotificationDao {

    Long create(Notification notification);

    Notification getById(Long id);
}
