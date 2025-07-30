package com.example.produtoisapi.productManagement.repositories;

import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    @Override
    Optional<Category> findById(Long idCategory);

    Optional<Category> findByName(String name);

}
