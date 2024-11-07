package com.enriquegonzalo.mapper;

import com.enriquegonzalo.entity.Price;
import com.enriquegonzalo.inditex.core.openapi.api.model.ApplicationDatesDTO;
import com.enriquegonzalo.inditex.core.openapi.api.model.GetApplicablePriceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(target = "finalPrice", source = "price")
    @Mapping(target = "applicablePriceList", source = "priceList")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "applicationDates", expression = "java(createApplicationDatesDTO(price.getStartDate(), price.getEndDate()))")
    GetApplicablePriceResponseDTO toDto(Price price);

    default ApplicationDatesDTO createApplicationDatesDTO(LocalDateTime startDate, LocalDateTime endDate) {
        return ApplicationDatesDTO.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

}
