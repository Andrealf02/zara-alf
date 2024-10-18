package com.zara.alf.application;


import com.zara.alf.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId);
}
