package com.zara.alf.application.service;

import com.zara.alf.application.PriceRepository;
import com.zara.alf.application.validator.PriceValidator;
import com.zara.alf.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {
    private final PriceRepository priceRepository;
    private final PriceValidator priceValidator;

    public PriceService(PriceRepository priceRepository, PriceValidator priceValidator) {
        this.priceRepository = priceRepository;
        this.priceValidator = priceValidator;
    }

    public List<Price> getApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
        priceValidator.validateApplicationDate(applicationDate);
        priceValidator.validateProductId(productId);
        priceValidator.validateBrandId(brandId);
        return priceRepository.findApplicablePrices(applicationDate, productId, brandId);
    }
}
