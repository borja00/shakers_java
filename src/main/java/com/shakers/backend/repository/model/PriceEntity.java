package com.shakers.backend.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Builder
@Data
@Entity
@Table(name = "prices", indexes = @Index(columnList = "brandId, productId"))
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;
    Integer brandId;
    // We are storing dates in UTC time
    Instant startDate;
    Instant endDate;
    Integer priceRateId;
    Integer productId;
    Integer priority;
    Integer price;
    String currency;
}

