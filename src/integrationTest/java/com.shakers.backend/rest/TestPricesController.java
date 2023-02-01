package com.shakers.backend.rest;

import com.tngtech.junit.dataprovider.DataProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class TestPricesController {
    @Autowired
    private WebTestClient webClient;


    @ParameterizedTest
    @MethodSource("provideDifferentDates")
    void should_get_product_prices(LocalDate date, LocalTime time, String expectedPrice, Integer expectedRate) {

        String brandId = "1";
        String productId = "35455";

        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("brands", brandId, "products", productId)
                        .queryParam("date", date.toString())
                        .queryParam("time", time.toString())
                        .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.brandId").isEqualTo(brandId)
                .jsonPath("$.productId").isEqualTo(productId)
                .jsonPath("$.rateId").isEqualTo(expectedRate)
                .jsonPath("$.priceValid.from").isNotEmpty()
                .jsonPath("$.priceValid.until").isNotEmpty()

                .jsonPath("$.price.amount").isEqualTo(expectedPrice)
                .jsonPath("$.price.currency").isEqualTo("EUR");

    }
    @DataProvider
    static Object [][] provideDifferentDates() {
        return new Object[][]{
                {LocalDate.of(2020, 6,14), LocalTime.of(10, 0), "35.50", 1},
                {LocalDate.of(2020, 6,14), LocalTime.of(16, 0), "25.45", 2},
                {LocalDate.of(2020, 6,14), LocalTime.of(21, 0), "35.50", 1},
                {LocalDate.of(2020, 6,15), LocalTime.of(10, 0), "30.50", 3},
                {LocalDate.of(2020, 6,16), LocalTime.of(21, 0), "38.95", 4},

        };

    }

    @ParameterizedTest
    @MethodSource("provideNonExistingProduct")
    void should_fail_when_no_product(Integer brandId, Integer productId) {

        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("brands", brandId.toString(), "products", productId.toString())
                        .queryParam("date", "2020-06-14")
                        .queryParam("time", "11:00")
                        .build()
                )
                .exchange()
                .expectStatus().isNotFound();
    }


    @DataProvider
    static Object [][] provideNonExistingProduct() {
        return new Object[][]{
                {9999, 35455},
                {1, 9999},

        };

    }
}
