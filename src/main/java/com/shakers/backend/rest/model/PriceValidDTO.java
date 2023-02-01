package com.shakers.backend.rest.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data

public class PriceValidDTO {

    Instant from;

    Instant until;


}
