package com.example.tradingnotifications.adapter.dao;

import com.example.tradingnotifications.IntegrationTest;
import com.example.tradingnotifications.domain.Stock;
import com.example.tradingnotifications.domain.StockType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StockDaoTest extends IntegrationTest {

    @Autowired
    StockDao stockDao;

    @BeforeEach
    void setup() {
        clearTables("stocks");
    }

    @Test
    void create_stock__when_does_not_exists() {
        // GIVEN
        Stock stock = Stock.builder()
                .type(StockType.SHARE)
                .ticker("GAZP")
                .name("Газпром")
                .build();

        // WHEN
        Long actualId = stockDao.create(stock);

        // THEN
        assertThat(findAll()).containsOnly(stock.withId(actualId));
    }

    private List<Stock> findAll() {
        return jdbcOperations.query("SELECT * FROM stocks", Map.of(), rowMapper);
    }

    private static final RowMapper<Stock> rowMapper = new StockRowMapper();

    private static class StockRowMapper implements RowMapper<Stock> {
        @Override
        public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Stock(
                    rs.getLong("id"),
                    StockType.valueOf(rs.getString("type")),
                    rs.getString("ticker"),
                    rs.getString("name")
            );
        }
    }
}