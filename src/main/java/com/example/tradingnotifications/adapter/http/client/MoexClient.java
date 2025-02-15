package com.example.tradingnotifications.adapter.http.client;

import com.example.tradingnotifications.adapter.http.response.SharesMoexResponse;
import com.example.tradingnotifications.infra.properties.MoexProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Клиент для Мосбиржи
 */
@Component
@RequiredArgsConstructor
public class MoexClient {

    private static final String ISS_MOEX_HOST = "https://iss.moex.com";
    private static final String MOEX_SHARES_PATH = "/iss/engines/stock/markets/shares/boards/TQBR/securities.json";
    private static final String MOEX_BONDS_PATH = "/iss/engines/stock/markets/bonds/boards/TQOB/securities.json";
    private static final String MOEX_CURRENCIES_PATH = "/iss/engines/currency/markets/selt/securities.json";

    private final MoexProperties moexProperties;
    private final RestTemplate restTemplate;

    public SharesMoexResponse getShares() {

        return restTemplate.exchange(
                ISS_MOEX_HOST + MOEX_SHARES_PATH,
                        HttpMethod.GET,
                        null,
                        SharesMoexResponse.class)
                .getBody();
    }
}
