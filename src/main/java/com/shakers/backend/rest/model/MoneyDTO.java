package com.shakers.backend.rest.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MoneyDTO {

    String amount;

    String currency;

}
