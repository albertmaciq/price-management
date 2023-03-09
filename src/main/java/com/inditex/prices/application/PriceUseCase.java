package com.inditex.prices.application;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.output.port.EntityRepository;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class PriceUseCase implements PriceInputPort {
    @Autowired
    private EntityRepository entityRepository;
    @Override
    public List<Price> obtainListProductPrice(final Integer brandId,
                                              final Integer productId,
                                              final LocalDateTime date) {
        return entityRepository.findByBrandIdProductAndDate(brandId, productId, date);
    }
}
