package com.inditex.prices.application.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.application.model.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceResponse mapperToResponse(Price price);
}
