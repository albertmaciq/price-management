package com.inditex.prices.infrastructure.output.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PriceResponse {

    private Integer productId;
    private Integer brandId;
    private BigDecimal price;
    private String startDate;
    private String endDate;
}
