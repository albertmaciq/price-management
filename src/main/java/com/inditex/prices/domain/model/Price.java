package com.inditex.prices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity
@Table(name = "prices")
public class Price {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "brand_id")
    private Integer brandId;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private Integer priceList;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "curr")
    private String curr;
}
