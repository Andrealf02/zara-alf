package com.zara.alf.application;

import com.zara.alf.application.validator.PriceValidator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceValidatorTest {

    private final PriceValidator priceValidator = new PriceValidator();

    @Test
    public void testValidateApplicationDateWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceValidator.validateApplicationDate(null);
        }, "La fecha de aplicación no puede ser nula");
    }


    @Test
    public void testValidateApplicationDateWithFutureDate() {
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        priceValidator.validateApplicationDate(futureDate);
    }

    @Test
    public void testValidateProductIdWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceValidator.validateProductId(null);
        }, "El ID del producto no puede ser nulo o negativo");
    }

    @Test
    public void testValidateProductIdWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceValidator.validateProductId(-1L);
        }, "El ID del producto no puede ser nulo o negativo");
    }

    @Test
    public void testValidateProductIdWithZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceValidator.validateProductId(0L);
        }, "El ID del producto no puede ser nulo o negativo");
    }

    @Test
    public void testValidateProductIdWithPositiveValue() {
        priceValidator.validateProductId(1L); // No debería lanzar excepción
    }

    @Test
    public void testValidateBrandIdWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceValidator.validateBrandId(null);
        }, "El ID de la marca no puede ser nulo o negativo");
    }

    @Test
    public void testValidateBrandIdWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceValidator.validateBrandId(-1L);
        }, "El ID de la marca no puede ser nulo o negativo");
    }

    @Test
    public void testValidateBrandIdWithZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            priceValidator.validateBrandId(0L);
        }, "El ID de la marca no puede ser nulo o negativo");
    }

    @Test
    public void testValidateBrandIdWithPositiveValue() {
        priceValidator.validateBrandId(1L);
    }
}
