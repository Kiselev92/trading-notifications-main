package com.example.tradingnotifications.adapter.dao;

import com.example.tradingnotifications.IntegrationTest;
import com.example.tradingnotifications.domain.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcNotificationDaoTest extends IntegrationTest {

    @Autowired
    private NotificationDao notificationDao;

    @Test
    void create_success() {
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

    // Вспомогательный код для тестов
    @Autowired
    NamedParameterJdbcOperations testJdbc;

    private List<Notification> findAll() {
        return testJdbc.query("SELECT * FROM notifications", Map.of(), rowMapper);
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
            return  timestamp == null ? null : timestamp.toInstant();
        }
    }

    @Test
    void request_success() {
        //GIVEN
        Notification notification = Notification.builder()
                .id(null)
                .stockId(100L)
                .targetValue(BigDecimal.valueOf(1000))
                .comment("My-comment")
                .build();
        //WHEN
        Long actualId = notificationDao.getById(Notification notification);
        //THEN
        assertThat(findAll())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("targetValue","comment", "created", "updated")
                .containsOnly(notification.withId(actualId));
    }
}