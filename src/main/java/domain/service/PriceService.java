package domain.service;

import domain.PriceRepository;
import domain.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepository.findApplicablePrices(applicationDate, productId, brandId);
    }
}
