package com.inditex.prices.infrastructure.output.adapter;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.output.port.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Repository
public class InMemoryRepository implements EntityRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Object findByBrandIdProductIdAndDate(final Integer brandId,
                                                final Integer productId,
                                                final LocalDateTime date) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT TOP 1 ID,PRODUCT_ID,BRAND_ID,PRICE,");
        query.append("START_DATE,END_DATE,CURR,PRICE_LIST,PRIORITY");
        query.append(" FROM PRICES");
        query.append(" WHERE BRAND_ID=? AND PRODUCT_ID=?");
        query.append(" AND ? BETWEEN START_DATE AND END_DATE ORDER BY PRIORITY DESC");

        return entityManager.createNativeQuery(String.valueOf(query), Price.class)
            .setParameter(1, brandId)
            .setParameter(2, productId)
            .setParameter(3, date)
            .getSingleResult();
    }
}
