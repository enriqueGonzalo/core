package com.enriquegonzalo.controller;

import com.enriquegonzalo.entity.Price;
import com.enriquegonzalo.inditex.core.openapi.api.model.GetApplicablePriceResponseDTO;
import com.enriquegonzalo.mapper.PriceMapper;
import com.enriquegonzalo.usecase.GetApplicablePriceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PriceApiControllerTest {
    @Mock
    private GetApplicablePriceUseCase getApplicablePriceUseCase;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceApiController priceApiController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void getApplicablePriceReturnsCorrectResponse() {
        GetApplicablePriceResponseDTO mockResponse = GetApplicablePriceResponseDTO.builder()
                .finalPrice(25.45F)
                .applicablePriceList(2)
                .productId(35455)
                .brandId(1)
                .applicationDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                .build();

        Price price = Price.builder().build();
        when(getApplicablePriceUseCase.getApplicablePrice(any(), eq(35455), eq(1))).thenReturn(price);
        when(priceMapper.toDto(price)).thenReturn(mockResponse);

        ResponseEntity<GetApplicablePriceResponseDTO> response = priceApiController
                .getApplicablePrice("2020-06-14 15:00:00", 35455, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
        assertEquals(35455, Objects.requireNonNull(response.getBody()).getProductId());
        assertEquals(1, response.getBody().getBrandId());
    }



}