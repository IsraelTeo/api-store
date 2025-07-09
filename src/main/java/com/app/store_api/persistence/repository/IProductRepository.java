package com.app.store_api.persistence.repository;

import com.app.store_api.domain.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID> {

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional(readOnly = true)
    List<Product> findAll(Specification<Product> specification, Pageable pageable);
}
