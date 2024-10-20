package com.zara.alf.infraestructure;

import com.zara.alf.application.service.PriceService;
import domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    public void shouldReturnCorrectPriceForDate2020_06_14_10_00() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00");
        Price expectedPrice = new Price(1L, date, LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455L, 0, 35.50, "EUR");

        when(priceService.getApplicablePrices(date, 35455L, 1L))
                .thenReturn(Collections.singletonList(expectedPrice));

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(35.50));
    }

    @Test
    public void shouldReturnCorrectPriceForDate2020_06_14_16_00() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00");
        Price expectedPrice = new Price(1L, date, LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455L, 1, 25.45, "EUR");

        when(priceService.getApplicablePrices(date, 35455L, 1L))
                .thenReturn(Collections.singletonList(expectedPrice));

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-14 16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(25.45));
    }

    @Test
    public void shouldReturnCorrectPriceForDate2020_06_14_21_00() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00");
        Price expectedPrice = new Price(1L, LocalDateTime.parse("2020-06-14T00:00:00"),
                LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455L, 0, 35.50, "EUR");

        when(priceService.getApplicablePrices(date, 35455L, 1L))
                .thenReturn(Collections.singletonList(expectedPrice));

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-14 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(35.50));
    }

    @Test
    public void shouldReturnCorrectPriceForDate2020_06_15_10_00() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00");
        Price expectedPrice = new Price(1L, date, LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455L, 1, 30.50, "EUR");

        when(priceService.getApplicablePrices(date, 35455L, 1L))
                .thenReturn(Collections.singletonList(expectedPrice));

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-15 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].price").value(30.50));
    }

    @Test
    public void shouldReturnEmptyForDateWithNoApplicablePrice() throws Exception {
        LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00");
        when(priceService.getApplicablePrices(date, 35455L, 1L))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/prices")
                        .param("applicationDate", "2020-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
