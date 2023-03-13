package com.inditex.prices.application;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.output.mapper.PriceMapper;
import com.inditex.prices.infrastructure.output.model.PriceResponse;
import com.inditex.prices.infrastructure.output.port.EntityRepository;
import com.inditex.prices.infrastructure.rest.input.port.PriceInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PriceUseCase implements PriceInputPort {

    @Autowired
    private EntityRepository entityRepository;

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public PriceResponse obtainProductPrice(final Integer brandId,
                                            final Integer productId,
                                            final LocalDateTime date) throws SQLException {

        log.info("Obtaining the product price from repository by brandId: {}, "
            + "productId: {} and date: {}", brandId, productId, date);

        PriceResponse priceResponse;
        try {
            Price price = entityRepository.findByBrandIdProductIdAndDate(brandId, productId, date);
            log.info("Price: {}", price);
            priceResponse = priceMapper.priceToPriceResponse(price);
            log.info("PriceResponse: {}", priceResponse);
        } catch (Exception ex) {
            throw new SQLException();
        }

        log.info("Price product was found");
        return priceResponse;
    }
}
