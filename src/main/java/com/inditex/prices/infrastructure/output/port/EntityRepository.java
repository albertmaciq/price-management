package com.inditex.prices.infrastructure.output.port;

import com.inditex.prices.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<Price, Integer> {

    String  query = "SELECT TOP 1 PRODUCT_ID,BRAND_ID,PRICE,START_DATE,END_DATE"
        + " FROM PRICES"
        + " WHERE BRAND_ID=:brandId"
        + " AND PRODUCT_ID=:productId"
        + " AND :date BETWEEN START_DATE AND END_DATE"
        + " ORDER BY PRIORITY DESC";
    @Query(value = query, nativeQuery = true)
    Price findByBrandIdProductIdAndDate(@Param("brandId") Integer brandId,
                                        @Param("productId") Integer productId,
                                        @Param("date") String date);
}
