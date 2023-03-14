package com.inditex.prices.infrastructure.rest.input.adapter;

import com.inditex.prices.infrastructure.output.model.PriceResponse;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/prices")
@Slf4j
public class PriceAPI {
    @Autowired
    private PriceInputPort priceInputPort;

    @GetMapping("/status")
    String status() {
        return "Estoy vivo";
    }

    @GetMapping(value = "/brands/{brandId}/products/{productId}")
    ResponseEntity<PriceResponse> getProductPrice(@PathVariable("brandId")  final Integer branId,
                                                  @PathVariable("productId") final Integer productId,
                                                  @RequestParam(value = "date")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                  final LocalDateTime date) throws SQLException {
        log.info("Obtaining the product price init");
        return ResponseEntity.ok(priceInputPort.obtainProductPrice(branId, productId, date));
    }
}
