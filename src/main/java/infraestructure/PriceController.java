package infraestructure;

import domain.model.Price;
import domain.service.PriceService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public List<Price> getPrices(
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {
        LocalDateTime dateTime = LocalDateTime.parse(applicationDate);
        return priceService.getApplicablePrices(dateTime, productId, brandId);
    }
}

