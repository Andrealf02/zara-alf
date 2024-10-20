package com.zara.alf.application;

import com.zara.alf.application.service.PriceService;
import com.zara.alf.application.validator.PriceValidator;
import domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    private PriceService priceService;
    private PriceRepository priceRepository;
    private PriceValidator priceValidator;

    @BeforeEach
    public void setUp() {
        priceRepository = Mockito.mock(PriceRepository.class);
        priceValidator = new PriceValidator();
        priceService = new PriceService(priceRepository, priceValidator);
    }

    @Test
    public void testGetApplicablePrices_Success() {
        LocalDateTime applicationDate = LocalDateTime.parse("2024-06-14T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        Price expectedPrice = new Price(1L, LocalDateTime.parse("2024-06-14T00:00:00"),
                LocalDateTime.parse("2024-12-31T23:59:59"), 1, productId, 1,35.50, "EUR");

        when(priceRepository.findApplicablePrices(applicationDate, productId, brandId))
                .thenReturn(List.of(expectedPrice));

        List<Price> prices = priceService.getApplicablePrices(applicationDate, productId, brandId);

        assertEquals(1, prices.size());
        assertEquals(expectedPrice, prices.get(0));
    }

    @Test
    public void testGetApplicablePrices_NoMatch() {
        LocalDateTime applicationDate = LocalDateTime.parse("2024-06-16T10:00:00");
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepository.findApplicablePrices(applicationDate, productId, brandId))
                .thenReturn(List.of());

        List<Price> prices = priceService.getApplicablePrices(applicationDate, productId, brandId);

        assertEquals(0, prices.size());
    }

    @Test
    public void testGetApplicablePrices_WithNullApplicationDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceService.getApplicablePrices(null, 35455L, 1L);
        });
    }

    @Test
    public void testGetApplicablePrices_WithNullProductId() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceService.getApplicablePrices(LocalDateTime.now(), null, 1L);
        });
    }

    @Test
    public void testGetApplicablePrices_WithNullBrandId() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceService.getApplicablePrices(LocalDateTime.now(), 35455L, null);
        });
    }
}
