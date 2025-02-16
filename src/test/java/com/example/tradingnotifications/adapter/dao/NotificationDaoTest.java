package com.example.tradingnotifications.adapter.dao;

import com.example.tradingnotifications.IntegrationTest;
import com.example.tradingnotifications.domain.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NotificationDaoTest extends IntegrationTest {

    @Autowired
    NotificationDao notificationDao;

    @Test
    void create__when_notification_does_not_exist() {
        // GIVEN
        Notification notification = Notification.builder()
                .id(null)
                .stockId(100L)
                .targetValue(BigDecimal.valueOf(1000))
                .comment("My-comment")
                .build();

        // WHEN
        Long actualId = notificationDao.create(notification);

        // THEN
        assertThat(findAll())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
                .containsOnly(notification.withId(actualId));
    }

    private List<Notification> findAll() {
        return testJdbc.query("SELECT * FROM notifications", Map.of(), rowMapper);
    }

    @Test
    void find_by_id__when_notification_exists() {
        //GIVEN
        Notification preparedNotification = Notification.builder()
                .stockId(100L)
                .targetValue(BigDecimal.valueOf(1000))
                .comment("My-comment")
                .build();

        Long notificationId = notificationDao.create(preparedNotification);

        //WHEN
        Notification actualNotification = notificationDao.findById(notificationId);

        //THEN
        assertThat(actualNotification)
                .usingRecursiveComparison()
                .ignoringFields("created", "updated")
                .isEqualTo(preparedNotification.withId(notificationId));
    }

    @Test
    void delete_notification__when_notification_exist() {
        //GIVEN
        Notification ownNotification = Notification.builder()
                .stockId(100L)
                .targetValue(BigDecimal.valueOf(1000))
                .comment("My-comment")
                .build();

        Notification otherNotification = Notification.builder()
                .stockId(100L)
                .targetValue(BigDecimal.valueOf(1000))
                .comment("My-comment")
                .build();

        Long ownNotificationId = notificationDao.create(ownNotification);
        Long otherNotificationId = notificationDao.create(otherNotification);

        //WHEN
        notificationDao.deleteById(ownNotificationId);

        //THEN
        assertThat(findAll())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("created", "updated")
                .doesNotContain(ownNotification.withId(ownNotificationId))
                .containsOnly(otherNotification.withId(otherNotificationId));
    }

    private static final RowMapper<Notification> rowMapper = new NotificationRowMapper();

    private static class NotificationRowMapper implements RowMapper<Notification> {
        @Override
        public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Notification.builder()
                    .id(rs.getLong("id"))
                    .stockId(rs.getLong("stock_id"))
                    .targetValue(rs.getBigDecimal("target_value"))
                    .comment(rs.getString("comment"))
                    .created(toInstant(rs.getTimestamp("created")))
                    .updated(toInstant(rs.getTimestamp("updated")))
                    .build();
        }

        private static Instant toInstant(Timestamp timestamp) {
            return timestamp == null ? null : timestamp.toInstant();
        }
    }
}
