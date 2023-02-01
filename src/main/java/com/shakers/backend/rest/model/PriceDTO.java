package com.shakers.backend.rest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class PriceDTO {

    Integer brandId;

    Integer productId;

    Integer rateId;

    MoneyDTO price;
}
