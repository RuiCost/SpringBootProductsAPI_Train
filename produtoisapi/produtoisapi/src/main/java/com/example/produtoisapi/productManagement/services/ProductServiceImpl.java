package com.example.produtoisapi.productManagement.services;

import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.userManagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<Product> searchProducts(String query, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if ((query == null || query.isBlank()) && (category == null || category.isBlank())) {
            return repository.findAllProducts(pageable);
        }

        return repository.searchWithFilters(query, category, pageable);
    }

    @Override
    public Product partialUpdate(String id, EditProductRequest resource, long desiredVersion, User user) {
        final var product = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot update a Product that does not yet exist"));

        // We wont force user to update both ->

        // Update price if needed
        if (resource.getPrice() != null) {
            if (resource.getPrice() < 0) {
                throw new IllegalArgumentException("Price cannot be under 0 euros!");
            }
            product.setPrice(resource.getPrice());
        }

        // Update desc if needed
        if (resource.getDescription() != null) {
            if (resource.getDescription().isBlank()) {
                throw new IllegalArgumentException("Description must not be blank");
            }
            product.setDescription(resource.getDescription());
        }

        // ✅ Add support for updating the imageURL
        if (resource.getImageURL() != null) {
            product.setImageURL(resource.getImageURL());
        }

        return repository.save(product);
    }


    @Override
    public int deleteById(String id, long desiredVersion) {
        return repository.deleteByIdIfMatch(id, desiredVersion);
    }


}
