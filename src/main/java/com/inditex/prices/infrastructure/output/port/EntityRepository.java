package com.inditex.prices.infrastructure.output.port;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface EntityRepository {

  List<Price> findByBrandIdProductAndDate(Integer brandId, Integer productId, LocalDateTime date);
}
