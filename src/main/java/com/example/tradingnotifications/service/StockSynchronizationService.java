package com.example.tradingnotifications.service;

import com.example.tradingnotifications.adapter.http.MoexAdapter;
import com.example.tradingnotifications.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockSynchronizationService {

    private final StockService stockService;
    private final MoexAdapter moexAdapter;

    public void synchronizeStocks() {
        List<Stock> shares = moexAdapter.getShares();
        stockService.upsert(shares);
    }
}
