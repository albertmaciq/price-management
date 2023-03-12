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
                                              final String date) throws SQLException {

        log.info("Obtaining the product price from repository init");
        PriceResponse priceResponse;
        try {
            Price price = entityRepository.findByBrandIdProductIdAndDate(brandId, productId, date);
            priceResponse = priceMapper.priceToPriceResponse(price);
        } catch (Exception ex) {
            throw new SQLException();
        }

        log.info("Product price was found");
        return priceResponse;
    }
}
