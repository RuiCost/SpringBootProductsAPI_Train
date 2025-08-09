package com.example.produtoisapi.productManagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.produtoisapi.productManagement.model.Product;
import org.springframework.security.access.method.P;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, String> {

    // Generic spring boot calls to repo

    @Override
    Optional<Product> findById(String idProduct);

    Optional<Product> findByName(String name);




    // Queries
    @Query("SELECT p FROM Product p ORDER BY p.name ASC")
    Page<Product> findAllProducts(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id = ?1 AND p.version = ?2")
    int deleteByIdIfMatch(String id, long desiredVersion);

    @Query(" SELECT p FROM Product p WHERE (:category IS NULL OR LOWER(p.category.name) = LOWER(:category))  AND (:query IS NULL OR  LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))) ")
    Page<Product> searchWithFilters(@Param("query") String query,
                                    @Param("category") String category,
                                    Pageable pageable);

}
