package com.shakers.backend.rest;

import com.shakers.backend.exceptions.ResourceNotFoundException;
import com.shakers.backend.rest.model.PriceDTO;
import com.shakers.backend.rest.model.builder.PriceDTOBuilder;
import com.shakers.backend.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class PricesController {

    private final ProductsService productsService;


    @Autowired
    public PricesController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("brands/{brandId}/products/{productId}")
    private Mono<PriceDTO> getEmployeeById(
            @PathVariable Integer brandId,
            @PathVariable Integer productId,
            @RequestParam(value = "date", required = false) LocalDate date,
            @RequestParam(value = "time", required = false) LocalTime time
            ) {
        if (date == null) {
            date = LocalDate.now();
        }
        if (time == null) {
            time = LocalTime.now();
        }
        return productsService.findPriceForProduct(brandId, productId, date.atTime(time))
                .map(PriceDTOBuilder::buildFrom)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException()));
    }

}