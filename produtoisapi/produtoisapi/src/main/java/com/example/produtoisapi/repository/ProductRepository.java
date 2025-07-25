package com.example.produtoisapi.repository;

import com.example.produtoisapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
