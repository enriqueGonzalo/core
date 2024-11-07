package com.enriquegonzalo.mapper;

import com.enriquegonzalo.entity.Price;
import com.enriquegonzalo.inditex.core.openapi.api.model.GetApplicablePriceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PriceMapper {

    @Mapping(target = "finalPrice", source = "price")
    @Mapping(target = "applicablePriceList", source = "priceList")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "applicationDate", source = "startDate")
    GetApplicablePriceResponseDTO toDto(Price price);



}
