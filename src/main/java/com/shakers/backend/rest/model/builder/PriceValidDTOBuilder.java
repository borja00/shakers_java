package com.shakers.backend.rest.model.builder;

import com.shakers.backend.model.Price;
import com.shakers.backend.rest.model.PriceValidDTO;

public class PriceValidDTOBuilder {


    public static PriceValidDTO buildFrom(Price price) {

        return PriceValidDTO.builder()
                .from(price.getStartDate())
                .until(price.getEndDate())
                .build();
    }
}
