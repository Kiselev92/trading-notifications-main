package com.example.tradingnotifications.adapter.http;

import com.example.tradingnotifications.adapter.http.client.MoexClient;
import com.example.tradingnotifications.adapter.http.response.SharesMoexResponse;
import com.example.tradingnotifications.domain.Stock;
import com.example.tradingnotifications.domain.StockType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MoexAdapter {

    private static final int SHARE_TICKER_POSITION = 0;
    private static final int SHARE_NAME_POSITION = 9;

    private final MoexClient moexClient;

    public List<Stock> getShares() {
        SharesMoexResponse sharesResponse = moexClient.getShares();
        Object[][] shares = sharesResponse.getSecurities().getData();
        return Arrays.stream(shares)
                .filter(share -> share[SHARE_TICKER_POSITION] != null && share[SHARE_NAME_POSITION] != null)
                .map((Object[] share) -> Stock.builder()
                        .ticker(share[SHARE_TICKER_POSITION].toString())
                        .type(StockType.SHARE)
                        .name(share[SHARE_NAME_POSITION].toString())
                        .build())
                .toList();
    }
}
