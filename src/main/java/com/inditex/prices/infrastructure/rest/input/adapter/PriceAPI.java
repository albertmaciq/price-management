package com.inditex.prices.infrastructure.rest.input.adapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/prices")
public class PriceAPI {

    @Autowired
    private PriceInputPort priceInputPort;

    @GetMapping(value = "/brands/{brand_id}/products/{product_id}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    List<Price> getListProductPrice(@PathVariable ("brand_id") final Integer branId,
                                    @PathVariable("product_id") final Integer productId,
                                    @RequestParam @DateTimeFormat final LocalDateTime date) {
        return priceInputPort.obtainListProductPrice(branId, productId, date);
    }
}
