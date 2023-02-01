package com.shakers.backend.rest.model.builder;

import com.shakers.backend.rest.model.MoneyDTO;
import org.javamoney.moneta.Money;

import java.math.RoundingMode;

public class MoneyDTOBuilder {


    public static MoneyDTO buildFrom(Money price) {

        return MoneyDTO.builder()
                .amount(price.getNumberStripped().setScale(price.getCurrency().getDefaultFractionDigits(), RoundingMode.HALF_EVEN).toString())
                .currency(price.getCurrency().getCurrencyCode())
                .build();
    }
}
