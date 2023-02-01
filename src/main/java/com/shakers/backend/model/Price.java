package com.shakers.backend.model;

import lombok.Builder;
import lombok.Data;
import org.javamoney.moneta.Money;

import java.time.Instant;

@Builder
@Data

public class Price {

    Integer brandId;
    Instant startDate;
    Instant endDate;
    Integer priceRateId;
    Integer productId;
    Integer priority;
    Money price;

}
