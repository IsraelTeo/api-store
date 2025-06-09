package com.app.store_api.persistence.listener;

import com.app.store_api.domain.Customer;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PrePersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class CustomerEntityListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEntityListener.class);

    @PrePersist
    public void prePersist(Customer customer) {
        LOGGER.info("Pre persist customer: {}", customer);
        customer.setCreationDate(LocalDate.now());
    }

    @PostPersist
    public void postPersist(Customer customer) {
        LOGGER.info("postPersist with this reservation {}", customer);
    }

    @PostRemove
    public void onPostRemove(Customer customer) {
        LOGGER.info("onPostRemove with this reservation {}", customer);
    }

    @PostLoad
    public void onPostLoad(Customer customer) {
        LOGGER.info("onPostLoad with this reservation {}", customer);
    }
}
