package com.inditex.prices.infrastructure.rest.input.port;

import com.inditex.prices.infrastructure.output.model.PriceResponse;

import java.sql.SQLException;

public interface PriceInputPort {

    PriceResponse obtainProductPrice(Integer brandId, Integer productId,
                                     String date) throws SQLException;
}
