package com.shakers.backend.services;


import com.shakers.backend.exceptions.ResourceNotFoundException;
import com.shakers.backend.repository.PricesRepository;
import com.shakers.backend.repository.model.builder.PriceEntityBuilder;
import com.shakers.backend.model.Price;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;

@Service
public class ProductsService {

    private final PricesRepository pricesRepository;

    @Autowired
    public ProductsService(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    public Mono<Price> findPriceForProduct(Integer brandId, Integer productId, @NotNull LocalDateTime time) {

        return Flux.fromIterable(pricesRepository.findByBrandIdAndProductId(brandId, productId))
                .map(PriceEntityBuilder::buildFrom)
                //TODO: Are ranges closed or opened? if closed add  isEqual in filter
                .filter(e -> e.getStartDate().isBefore(time))
                .filter(e -> e.getEndDate().isAfter(time))
                .sort(Comparator.comparing(Price::getPriority).reversed())
                .take(1)
                .singleOrEmpty();

    }
}
