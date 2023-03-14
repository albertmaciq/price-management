package com.inditex.prices.infrastructure.output.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.output.model.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceResponse mapperToResponse(Price price);
}
