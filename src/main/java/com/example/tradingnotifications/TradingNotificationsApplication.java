package com.example.tradingnotifications;

import com.example.tradingnotifications.infra.properties.MoexProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        MoexProperties.class
})
public class TradingNotificationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingNotificationsApplication.class, args);
    }
}
