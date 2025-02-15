package com.example.tradingnotifications.adapter.http.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class SharesMoexResponse {

    private Securities securities;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Securities {

        private Object[][] data;
    }
}
