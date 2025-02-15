package com.example.tradingnotifications.infra.properties;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "moex")
public class MoexProperties {
    String url;
    Credentials credentials;

    @Value
    public static class Credentials {
        String login;
        String password;
    }
}
