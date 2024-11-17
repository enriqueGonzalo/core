package com.enriquegonzalo.usecase;

import com.enriquegonzalo.entity.Price;
import com.enriquegonzalo.repository.GetApplicablePriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class GetApplicablePriceUseCaseImplTest {

    @Mock
    private GetApplicablePriceRepository getApplicablePriceRepository;

    @InjectMocks
    private GetApplicablePriceUseCaseImpl getApplicablePriceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getApplicablePriceReturnsPriceForValidInput() {
        Price mockPrice = Price.builder()
                .brandId(1L)
                .priceList(2)
                .productId(35455)
                .price(new BigDecimal("25.45"))
                .startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                .build();

        when(getApplicablePriceRepository.findPrice(eq(35455), eq(1L), any(LocalDateTime.class)))
                .thenReturn(Optional.of(mockPrice));

        Price result = getApplicablePriceUseCase.getApplicablePrice("2020-06-14T15:00:00", 35455, 1);

        assertNotNull(result);
        assertEquals(35455, result.getProductId());
        assertEquals(1L, result.getBrandId());
    }

    @Test
    void getApplicablePriceReturnsNewPriceForInvalidInput() {
        when(getApplicablePriceRepository.findPrice(eq(99999), eq(1L), any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        Price result = getApplicablePriceUseCase.getApplicablePrice("2020-06-14T15:00:00", 99999, 1);

        assertNotNull(result);
        assertNull(result.getProductId());
        assertNull(result.getBrandId());
    }

    @Test
    void getApplicablePriceThrowsExceptionForInvalidDate() {
        assertThrows(RuntimeException.class, () -> getApplicablePriceUseCase.getApplicablePrice("invalid-date", 35455, 1));
    }

    @Test
    void getApplicablePriceHandlesNullBrandId() {
        assertThrows(NullPointerException.class, () -> getApplicablePriceUseCase.getApplicablePrice("2020-06-14T15:00:00", 35455, null));
    }
}