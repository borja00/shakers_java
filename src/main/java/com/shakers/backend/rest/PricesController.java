package com.shakers.backend.rest;

import com.shakers.backend.exceptions.ResourceNotFoundException;
import com.shakers.backend.rest.model.PriceDTO;
import com.shakers.backend.rest.model.builder.PriceDTOBuilder;
import com.shakers.backend.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.*;

@RestController
public class PricesController {

    private final ProductsService productsService;


    @Autowired
    public PricesController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("brands/{brandId}/products/{productId}")
    private Mono<PriceDTO> getProductPrice(
            @PathVariable Integer brandId,
            @PathVariable Integer productId,
            @RequestParam(value = "date", required = false) LocalDate date,
            @RequestParam(value = "time", required = false) LocalTime time,
            @RequestParam(value = "timezone", required = false) ZoneId zoneId
            ) {
        if (date == null) {
            date = LocalDate.now();
        }

        if (time == null) {
            time = LocalTime.now();
        }

        if(zoneId == null) {
            zoneId= ZoneOffset.UTC;
        }

        return productsService.findPriceForProduct(brandId, productId, LocalDateTime.of(date, time).atZone(zoneId).toInstant())
                .map(PriceDTOBuilder::buildFrom)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
    }

}