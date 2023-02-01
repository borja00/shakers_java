package com.shakers.backend.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    // We are storing dates in UTC time and without Timezone
    LocalDateTime startDate;
    LocalDateTime endDate;
    Integer priceRateId;
    Integer productId;
    Integer priority;
    Integer price;
    String currency;
}

