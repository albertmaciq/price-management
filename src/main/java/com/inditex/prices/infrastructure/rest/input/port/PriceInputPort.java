package com.inditex.prices.infrastructure.rest.input.port;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceInputPort {

    List<Price> obtainListProductPrice(Integer brandId, Integer productId, LocalDateTime date);
}
