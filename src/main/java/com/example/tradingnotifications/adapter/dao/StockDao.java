package com.example.tradingnotifications.adapter.dao;

import com.example.tradingnotifications.domain.Stock;
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
public class StockDao {

    private final NamedParameterJdbcOperations jdbc;

    public Long create(Stock stock) {
        var sql = """
                INSERT INTO stocks (id, type, ticker, name, created, updated)
                VALUES (default, :type, :ticker, :name, :created, :updated)
                """;
        Instant now = Instant.now();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("type", stock.getType().name())
                .addValue("ticker", stock.getTicker())
                .addValue("name", stock.getName())
                .addValue("created", Timestamp.from(now))
                .addValue("updated", Timestamp.from(now));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sql, params, keyHolder);
        return (Long) keyHolder.getKeys().get("id");
    }
}
