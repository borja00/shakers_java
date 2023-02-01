package com.shakers.backend.rest.model.builder;

import com.shakers.backend.model.Price;
import com.shakers.backend.rest.model.PriceDTO;

public class PriceDTOBuilder {


    public static PriceDTO buildFrom(Price price) {
        return PriceDTO.builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .price(MoneyDTOBuilder.buildFrom(price.getPrice()))
                .rateId(price.getPriceRateId())
                .build();
    }
}
