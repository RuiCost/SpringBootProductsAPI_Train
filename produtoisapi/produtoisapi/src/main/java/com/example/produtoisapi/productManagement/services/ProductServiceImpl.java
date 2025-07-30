package com.example.produtoisapi.productManagement.services;

import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.userManagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.produtoisapi.exceptions.NotFoundException;

import java.util.Optional;

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

    @Override
    public Optional<Product> findProductByID(String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Iterable<Product> findAll(int page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllProducts(pageable);
    }

    @Override
    public Product partialUpdate(String id, EditProductRequest resource, long desiredVersion, User user) {
        final var product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot update a Product that does not yet exist"));

        product.applyPatch(desiredVersion,resource.getPrice(),resource.getDescription());

        return repository.save(product);


    }


}
