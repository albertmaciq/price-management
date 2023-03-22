package com.inditex.prices.infrastructure.rest.input.port;

import com.inditex.prices.application.model.PriceResponse;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface PriceInputPort {
    PriceResponse obtainProductPrice(Integer brandId, Integer productId,
                                     LocalDateTime date) throws SQLException;
}
