package com.inditex.prices.infrastructure.output.adapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.mapper.PriceRowMapper;
import com.inditex.prices.infrastructure.output.port.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class InMemoryEntityRepository implements EntityRepository {

    private final String query = "SELECT * "
            + " FROM PRICES"
            + " WHERE BRAND_ID=?"
            + " AND PRODUCT_ID=?"
            + " AND ? BETWEEN START_DATE AND END_DATE";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Price> findByBrandIdProductAndDate(final Integer brandId,
                                                   final Integer productId,
                                                   final LocalDateTime date) {
        return jdbcTemplate.query(query, new PriceRowMapper(), brandId, productId, date);

    }
}
