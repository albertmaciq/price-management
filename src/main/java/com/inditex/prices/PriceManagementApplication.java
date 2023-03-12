package com.inditex.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.inditex.prices.infrastructure.output.mapper.PriceMapper"})
public class PriceManagementApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PriceManagementApplication.class, args);
    }
}
