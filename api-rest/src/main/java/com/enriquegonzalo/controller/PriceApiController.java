package com.enriquegonzalo.controller;

import com.enriquegonzalo.inditex.core.openapi.api.PricesApi;
import com.enriquegonzalo.inditex.core.openapi.api.model.GetApplicablePriceResponseDTO;
import com.enriquegonzalo.mapper.PriceMapper;
import com.enriquegonzalo.usecase.GetApplicablePriceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PriceApiController implements PricesApi {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<GetApplicablePriceResponseDTO> getApplicablePrice(String applicationDate, Integer productId, Integer brandId) {
        log.info("getPrices");
        return ResponseEntity.ok(this.priceMapper.toDto(this.getApplicablePriceUseCase.getApplicablePrice(applicationDate, Math.toIntExact(Long.valueOf(productId)), brandId)));
    }
}
