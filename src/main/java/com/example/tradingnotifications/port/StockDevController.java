package com.example.tradingnotifications.port;

import com.example.tradingnotifications.domain.Stock;
import com.example.tradingnotifications.port.request.StockCreateRequest;
import com.example.tradingnotifications.service.StockService;
import com.example.tradingnotifications.service.StockSynchronizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dev/stock")
@RequiredArgsConstructor
public class StockDevController {

    private final StockService stockService;
    private final StockSynchronizationService stockSynchronizationService;

    /**
     * Создать сток (акция, облигация, ...)
     *
     * @param request - поля стока (тело POST запроса)
     * @return идентификатор стока
     */
    @PostMapping
    public Long create(@RequestBody StockCreateRequest request) {
        return stockService.create(toModel(request));
    }

    private static Stock toModel(StockCreateRequest request) {
        return Stock.builder()
                .type(request.getType())
                .ticker(request.getTicker())
                .name(request.getName())
                .build();
    }

    @PostMapping("/sync")
    public void syncStocks() {
        stockSynchronizationService.synchronizeStocks();
    }
}
