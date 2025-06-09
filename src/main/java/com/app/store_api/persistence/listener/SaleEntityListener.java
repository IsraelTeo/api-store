package com.app.store_api.persistence.listener;

import com.app.store_api.domain.Sale;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class SaleEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleEntityListener.class);

    @PrePersist
    public void prePersist(Sale sale) {
        LOGGER.info("Pre persist sale: {}", sale);
        sale.setCreationDate(LocalDate.now());
    }

    @PostPersist
    public void postPersist(Sale sale) {
        LOGGER.info("postPersist with this sale: {}", sale);
    }

    @PostRemove
    public void onPostRemove(Sale sale) {
        LOGGER.info("onPostRemove with this sale: {}", sale);
    }

    @PostLoad
    public void onPostLoad(Sale sale) {
        LOGGER.info("onPostLoad with this sale: {}", sale);
    }
}
