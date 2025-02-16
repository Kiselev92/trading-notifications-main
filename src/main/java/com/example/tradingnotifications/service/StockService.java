package com.example.tradingnotifications.service;

import com.example.tradingnotifications.adapter.dao.StockDao;
import com.example.tradingnotifications.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockDao stockDao;

    public Long create(Stock stock) {
        return stockDao.create(stock);
    }

    public void upsert(List<Stock> stocks) {
        stockDao.create(stocks);
    }
}
