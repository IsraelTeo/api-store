package com.app.store_api.persistence.repository;

import com.app.store_api.domain.Customer;
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
public interface ICustomerRepository extends JpaRepository<Customer, UUID> {

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional(readOnly = true)
    List<Customer> findAll(Specification<Customer> specification, Pageable pageable);
}
