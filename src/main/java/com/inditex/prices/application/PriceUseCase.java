package com.inditex.prices.application;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.application.mapper.PriceMapper;
import com.inditex.prices.application.model.PriceResponse;
import com.inditex.prices.infrastructure.output.port.EntityRepository;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class PriceUseCase implements PriceInputPort {

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceResponse obtainProductPrice(final Integer brandId,
                                            final Integer productId,
                                            final LocalDateTime date) {

        log.info("Obtaining the product price from repository by brandId: {}, "
            + "productId: {} and date: {}", brandId, productId, date);

        Price price = (Price) entityRepository
            .findByBrandIdProductIdAndDate(brandId, productId, date);

        log.info("Price: {}", price);

        PriceResponse priceResponse = priceMapper.mapperToResponse(price);
        log.info("PriceResponse: {}", priceResponse);

        log.info("Price product was found");
        return priceResponse;
    }
}
