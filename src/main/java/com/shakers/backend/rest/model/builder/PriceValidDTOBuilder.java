package com.shakers.backend.rest.model.builder;

import com.shakers.backend.model.Price;
import com.shakers.backend.rest.model.MoneyDTO;
import com.shakers.backend.rest.model.PriceValidDTO;
import org.javamoney.moneta.Money;

import java.math.RoundingMode;

public class PriceValidDTOBuilder {


    public static PriceValidDTO buildFrom(Price price) {

        return PriceValidDTO.builder()
                .from(price.getStartDate())
                .until(price.getEndDate())
                .build();
    }
}
