package com.app.store_api.persistence.listener;

import com.app.store_api.domain.Product;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class ProductEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEntityListener.class);

    @PrePersist
    public void prePersist(Product product) {
        LOGGER.info("Pre persist product: {}", product);
        product.setCreationDate(LocalDate.now());
    }

    @PostPersist
    public void postPersist(Product product) {
        LOGGER.info("postPersist with this product: {}", product);
    }

    @PostRemove
    public void onPostRemove(Product product) {
        LOGGER.info("onPostRemove with this product: {}", product);
    }

    @PostLoad
    public void onPostLoad(Product product) {
        LOGGER.info("onPostLoad with this product: {}", product);
    }
}
