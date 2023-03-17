package com.inditex.prices.infrastructure.output.port;

import java.time.LocalDateTime;

public interface EntityRepository {

    Object findByBrandIdProductIdAndDate(Integer brandId, Integer productId, LocalDateTime date);
}
