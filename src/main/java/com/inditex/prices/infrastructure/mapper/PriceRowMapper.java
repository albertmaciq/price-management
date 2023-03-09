package com.inditex.prices.infrastructure.mapper;

import com.inditex.prices.domain.model.Price;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PriceRowMapper implements RowMapper<Price> {
    private LocalDateTime convertToLocalDateTime(final Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    public Price mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        Price price = new Price();
        price.setProductId(rs.getInt("PRODUCT_ID"));
        price.setBrandId(rs.getInt("BRAND_ID"));
        price.setPriceList(rs.getInt("PRICE_LIST"));
        price.setStartDate(convertToLocalDateTime(rs.getDate("START_DATE")));
        price.setEndDate(convertToLocalDateTime(rs.getDate("END_DATE")));
        price.setPrice(rs.getBigDecimal("PRICE"));

        return price;
    }
}
// probar simplificar con @Mapper
