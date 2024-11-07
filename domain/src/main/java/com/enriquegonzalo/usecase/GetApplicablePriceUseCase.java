package com.enriquegonzalo.usecase;

import com.enriquegonzalo.entity.Price;

import java.util.Optional;

public interface GetApplicablePriceUseCase {

    Price getApplicablePrice(String applicationDate, Integer productId, Integer brandId);

}
