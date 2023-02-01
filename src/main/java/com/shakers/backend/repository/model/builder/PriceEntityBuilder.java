package com.shakers.backend.repository.model.builder;

import com.shakers.backend.model.Price;
import com.shakers.backend.repository.model.PriceEntity;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import java.time.ZoneOffset;


public class PriceEntityBuilder {


    public static Price buildFrom(PriceEntity entity) {

        String currencyCode = entity.getCurrency();

        return Price.builder()
                .brandId(entity.getBrandId())
                .startDate(entity.getStartDate().toInstant(ZoneOffset.UTC))
                .endDate(entity.getEndDate().toInstant(ZoneOffset.UTC))
                .priceRateId(entity.getPriceRateId())
                .productId(entity.getProductId())
                .priority(entity.getPriority())
                .price(Money.of(entity.getPrice() / (Math.pow(10, Monetary.getCurrency(currencyCode).getDefaultFractionDigits()))
                        , currencyCode))
                .build();
    }
}
