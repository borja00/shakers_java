package com.shakers.backend.repository;

import com.shakers.backend.repository.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricesRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByBrandIdAndProductId(Integer brandId, Integer productId);


}