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


    // Queries


}
