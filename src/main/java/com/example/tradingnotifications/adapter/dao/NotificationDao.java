package com.example.tradingnotifications.adapter.dao;

import com.example.tradingnotifications.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
@RequiredArgsConstructor
public class NotificationDao {
    private final NamedParameterJdbcOperations jdbc;


    public Long create(Notification notification) {
        String sql = """
                INSERT INTO notifications (id, target_value, stock_id, comment, created, updated)
                VALUES (default, :targetValue, :stockId, :comment, :created, :updated)
                """;

        Instant now = Instant.now();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("targetValue", notification.getTargetValue())
                .addValue("stockId", notification.getStockId())
                .addValue("comment", notification.getComment())
                .addValue("created", Timestamp.from(now))
                .addValue("updated", Timestamp.from(now));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, keyHolder);
        return (Long) keyHolder.getKeys().get("id");
    }

    public Notification findById(Long id) {
        String sql = """
                SELECT * FROM notifications WHERE id = :id""";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        return jdbc.query(sql, params, rs -> {
            rs.next();
            return new Notification(
                    rs.getLong("id"),
                    rs.getLong("stock_id"),
                    rs.getBigDecimal("target_value"),
                    rs.getString("comment"),
                    rs.getTimestamp("created").toInstant(),
                    rs.getTimestamp("updated").toInstant()
            );
        });
    }

    public void deleteById(Long id) {
        String sql = """
                DELETE FROM notifications WHERE id = :id""";

        SqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbc.update(sql, params);
    }
}
