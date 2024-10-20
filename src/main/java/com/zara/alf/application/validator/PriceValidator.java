package com.zara.alf.application.validator;

import java.time.LocalDateTime;

public class PriceValidator {

    public void validateApplicationDate(LocalDateTime applicationDate) {
        if (applicationDate == null) {
            throw new IllegalArgumentException("La fecha de aplicación no puede ser nula");
        }
    }

    public void validateProductId(Long productId) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo o negativo");
        }
    }

    public void validateBrandId(Long brandId) {
        if (brandId == null || brandId <= 0) {
            throw new IllegalArgumentException("El ID de la marca no puede ser nulo o negativo");
        }
    }
}
