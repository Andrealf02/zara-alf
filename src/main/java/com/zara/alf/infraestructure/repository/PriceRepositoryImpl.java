package com.zara.alf.infraestructure.repository;


import com.zara.alf.application.PriceRepository;
import com.zara.alf.model.Price;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PriceRepositoryImpl implements PriceRepository {
    private List<Price> prices = new ArrayList<>();

    @PostConstruct
    public void init() {
        prices.add(new Price(1L, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455L, 0, 35.50, "EUR"));
        prices.add(new Price(1L, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455L, 1, 25.45, "EUR"));
        prices.add(new Price(1L, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455L, 1, 30.50, "EUR"));
        prices.add(new Price(1L, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455L, 1, 38.95, "EUR"));
    }

    @Override
    public List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
        return prices.stream()
                .filter(price -> price.getBrandId().equals(brandId) &&
                        price.getProductId().equals(productId) &&
                        !applicationDate.isBefore(price.getStartDate()) &&
                        !applicationDate.isAfter(price.getEndDate()))
                .toList();
    }
}
