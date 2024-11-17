package com.enriquegonzalo.repository;

import com.enriquegonzalo.entity.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface GetApplicablePriceRepository {

    Optional<Price> findPrice(Integer productId, Long brandId,  LocalDateTime date);

}
