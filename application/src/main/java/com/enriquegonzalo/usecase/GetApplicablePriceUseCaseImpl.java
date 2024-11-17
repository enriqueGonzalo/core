package com.enriquegonzalo.usecase;

import com.enriquegonzalo.entity.Price;
import com.enriquegonzalo.repository.GetApplicablePriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetApplicablePriceUseCaseImpl implements GetApplicablePriceUseCase {

    private final GetApplicablePriceRepository getApplicablePriceRepository;

    @Override
    public Price getApplicablePrice(String applicationDate, Integer productId, Integer brandId) {
        log.info("GetApplicablePriceUseCaseImpl");
        Optional<Price> optionalPrice = this.getApplicablePriceRepository.findPrice(productId, Long.valueOf(brandId), LocalDateTime.parse(applicationDate));
        return optionalPrice.orElseGet(Price::new);
    }
}
