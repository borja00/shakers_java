package com.shakers.backend.rest.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Data

public class PriceDTO {

    Integer brandId;

    Integer productId;

    Integer rateId;

    MoneyDTO price;

    Instant priceValidFrom;

    Instant priceValidUntil;


}
