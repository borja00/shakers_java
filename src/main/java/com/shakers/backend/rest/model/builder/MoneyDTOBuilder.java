package com.shakers.backend.rest.model.builder;

import com.shakers.backend.model.Price;
import com.shakers.backend.rest.model.MoneyDTO;
import com.shakers.backend.rest.model.PriceDTO;
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.math.RoundingMode;
import java.util.Locale;

public class MoneyDTOBuilder {


    public static MoneyDTO buildFrom(Money price) {

        return MoneyDTO.builder()
                .amount(price.getNumberStripped().setScale(price.getCurrency().getDefaultFractionDigits(), RoundingMode.HALF_EVEN).toString())
                .currency(price.getCurrency().getCurrencyCode())
                .build();
    }
}
