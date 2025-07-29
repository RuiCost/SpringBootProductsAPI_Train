package com.example.produtoisapi.productManagement.services;

import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.userManagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final EditProductMapper editProductMapper;

    @Override
    public Product create(CreateProductRequest resource) {
        final Product product = editProductMapper.create(resource);
        return repository.save(product);
    }


}
