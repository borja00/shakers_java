package com.shakers.backend.repository.model.builder;

import com.shakers.backend.model.Price;
import com.shakers.backend.repository.model.PriceEntity;
import org.javamoney.moneta.Money;

import javax.money.Monetary;


public class PriceEntityBuilder {


    public static Price buildFrom(PriceEntity entity) {

        String currencyCode = entity.getCurrency();

        return Price.builder()
                .brandId(entity.getBrandId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceRateId(entity.getPriceRateId())
                .productId(entity.getProductId())
                .priority(entity.getPriority())
                .price(Money.of(entity.getPrice() / (Math.pow(10, Monetary.getCurrency(currencyCode).getDefaultFractionDigits()))
                        , currencyCode))
                .build();
    }
}
