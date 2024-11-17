package com.enriquegonzalo.mapper;

import com.enriquegonzalo.entity.Price;
import com.enriquegonzalo.inditex.core.openapi.api.model.GetApplicablePriceResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class PriceMapperTest {

    private PriceMapper priceMapper;

    @BeforeEach
    void setUp() {
        priceMapper = Mappers.getMapper(PriceMapper.class);
    }

    @Test
    void shouldMapPriceEntityToDtoWithValidData() {
        Price price = Price.builder()
                .brandId(1L)
                .priceList(2)
                .productId(35455)
                .price(new BigDecimal("35.50"))
                .startDate(LocalDateTime.of(2024, 11, 12, 10, 0))
                .build();

        GetApplicablePriceResponseDTO responseDTO = priceMapper.toDto(price);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getBrandId()).isEqualTo(price.getBrandId().intValue());
        assertThat(responseDTO.getApplicablePriceList()).isEqualTo(price.getPriceList());
        assertThat(responseDTO.getProductId()).isEqualTo(price.getProductId());
        assertThat(responseDTO.getFinalPrice()).isEqualTo(price.getPrice().floatValue());
        assertThat(responseDTO.getApplicationDate()).isEqualTo(price.getStartDate());
    }

    @Test
    void shouldMapPriceEntityToDtoWithNullValues() {
        Price price = Price.builder()
                .brandId(null)
                .priceList(null)
                .productId(null)
                .price(null)
                .startDate(null)
                .build();

        GetApplicablePriceResponseDTO responseDTO = priceMapper.toDto(price);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getBrandId()).isNull();
        assertThat(responseDTO.getApplicablePriceList()).isNull();
        assertThat(responseDTO.getProductId()).isNull();
        assertThat(responseDTO.getFinalPrice()).isNull();
        assertThat(responseDTO.getApplicationDate()).isNull();
    }

    @Test
    void shouldMapPriceEntityToDtoWithNegativeValues() {
        Price price = Price.builder()
                .brandId(-1L)
                .priceList(-2)
                .productId(-35455)
                .price(new BigDecimal("-35.50"))
                .startDate(LocalDateTime.of(2024, 11, 12, 10, 0))
                .build();

        GetApplicablePriceResponseDTO responseDTO = priceMapper.toDto(price);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getBrandId()).isEqualTo(price.getBrandId().intValue());
        assertThat(responseDTO.getApplicablePriceList()).isEqualTo(price.getPriceList());
        assertThat(responseDTO.getProductId()).isEqualTo(price.getProductId());
        assertThat(responseDTO.getFinalPrice()).isEqualTo(price.getPrice().floatValue());
        assertThat(responseDTO.getApplicationDate()).isEqualTo(price.getStartDate());
    }
}
