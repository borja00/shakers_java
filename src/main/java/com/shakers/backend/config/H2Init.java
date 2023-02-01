package com.shakers.backend.config;

import com.shakers.backend.repository.PricesRepository;
import com.shakers.backend.repository.model.PriceEntity;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Slf4j
@Configuration
public class H2Init {

    static String dateTimePattern = "yyyy-MM-dd-HH.mm.ss";

    private final DateTimeFormatter dateTimeFormatter;
    private final PricesRepository pricesRepository;

    @Autowired
    public H2Init(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);

    }
    @PostConstruct
    private void populateDatabase() {

        log.info("Populating database");

        initialData().forEach(pricesRepository::save);

    }

    private List<PriceEntity> initialData() {
        return List.of(
                buildPrice("2020-06-14-00.00.00", "2020-12-31-23.59.59", 1, 0, 3550),
                buildPrice("2020-06-14-15.00.00", "2020-06-14-18.30.00", 2, 1, 2545),
                buildPrice("2020-06-15-00.00.00", "2020-06-15-11.00.00", 3, 1, 3050),
                buildPrice("2020-06-15-16.00.00", "2020-12-31-23.59.59", 4, 1, 3895)

                );
    }

    private PriceEntity buildPrice(String startDate, String endDate, Integer priceRateId, Integer priority, Integer price) {
        return                PriceEntity.builder()
                        .brandId(1)
                        .startDate(LocalDateTime.parse(startDate, dateTimeFormatter).toInstant(ZoneOffset.UTC))
                        .endDate(LocalDateTime.parse(endDate, dateTimeFormatter).toInstant(ZoneOffset.UTC))
                        .priceRateId(priceRateId)
                        .productId(35455)
                        .priority(priority)
                        .price(price)
                        .currency("EUR")
                        .build();
    }

}
