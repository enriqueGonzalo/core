package com.enriquegonzalo.repository;

import com.enriquegonzalo.entity.Price;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@SpringBootTest

public class GetApplicablePriceRepositoryImplTest {

    @InjectMocks
    private GetApplicablePriceRepositoryImpl repository;

    @Test
    public void testFindPrice_At10_day_14() {
        Optional<Price> result = repository.findPrice(35455, 1L, LocalDateTime.of(2024, 6, 14, 10, 0));
        assertTrue(result.isPresent());
        assertEquals(2, result.get().getPriceList());
        assertEquals(25.45, result.get().getPrice().doubleValue());
    }


    public void testFindPrice_At16_day_14() {
        Optional<Price> result = repository.findPrice(35455, 1L, LocalDateTime.of(2024, 6, 14, 16, 0));
        assertTrue(result.isPresent());
        assertEquals(2, result.get().getPriceList());
        assertEquals(25.45, result.get().getPrice().doubleValue());
    }

    public void testFindPrice_At21_day_14() {
        Optional<Price> result = repository.findPrice(35455, 1L, LocalDateTime.of(2024, 6, 14, 21, 0));
        assertTrue(result.isPresent());
        assertEquals(3, result.get().getPriceList());
        assertEquals(30.50, result.get().getPrice().doubleValue());
    }


    public void testFindPrice_At10_day_15() {
        Optional<Price> result = repository.findPrice(35455, 1L, LocalDateTime.of(2024, 6, 15, 10, 0));
        assertTrue(result.isPresent());
        assertEquals(4, result.get().getPriceList());
        assertEquals(38.95, result.get().getPrice().doubleValue());
    }


    public void testFindPrice_At21O_day_16() {
        Optional<Price> result = repository.findPrice(35455, 1L, LocalDateTime.of(2024, 6, 16, 21, 0));
        assertTrue(result.isPresent());
        assertEquals(4, result.get().getPriceList());
        assertEquals(38.95, result.get().getPrice().doubleValue());
    }
}
