package com.app.store_api.persistence.repository;

import com.app.store_api.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, UUID> {
}
