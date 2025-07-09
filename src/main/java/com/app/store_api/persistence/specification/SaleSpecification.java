package com.app.store_api.persistence.specification;

import com.app.store_api.domain.Customer;
import com.app.store_api.domain.Product;
import com.app.store_api.domain.Sale;
import com.app.store_api.dto.criteria.SearchSaleCriteriaDto;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SaleSpecification {

    public static Specification<Sale> withSearchCriteria (SearchSaleCriteriaDto criteriaDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteriaDto.getSaleId() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("saleId"), criteriaDto.getSaleId())
                );
            }

            if (criteriaDto.getCreationDate() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("creationDate"), criteriaDto.getCreationDate())
                );
            }

            if (criteriaDto.getCustomerId() != null) {
                Join<Sale, Customer> customerJoin = root.join("customer", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(customerJoin.get("customerId"), criteriaDto.getCustomerId()));
            }

            if (criteriaDto.getCustomerName() != null) {
                Join<Sale, Customer> customerJoin = root.join("customer", JoinType.INNER);
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(customerJoin.get("name")),
                                "%" + criteriaDto.getCustomerName().toLowerCase() + "%"
                        )
                );
            }

            if (criteriaDto.getProductIds() != null && !criteriaDto.getProductIds().isEmpty()) {
                Join<Sale, Product> productJoin = root.join("products", JoinType.INNER);
                predicates.add(productJoin.get("productId").in(criteriaDto.getProductIds()));
            }

            if (criteriaDto.getSortingDirection() != null && criteriaDto.getSortField() != null) {
                assert query != null;
                if (criteriaDto.getSortingDirection().equalsIgnoreCase("desc")) {
                    query.orderBy(
                            criteriaBuilder.desc(root.get(criteriaDto.getSortField()))
                    );
                } else {
                    query.orderBy(
                            criteriaBuilder.asc(root.get(criteriaDto.getSortField()))
                    );
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
