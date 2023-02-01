package com.shakers.backend.model;

import lombok.Builder;
import lombok.Data;
import org.javamoney.moneta.Money;

import java.time.Instant;
import java.time.LocalDateTime;


@Builder
@Data

public class Price {

    Integer brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceRateId;
    Integer productId;
    Integer priority;
    Money price;

}
