package com.enriquegonzalo.repository;

import com.enriquegonzalo.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetApplicablePriceRepository {


    Optional<Price> findPrice(Integer productId, Long brandId,  LocalDateTime date);

}
