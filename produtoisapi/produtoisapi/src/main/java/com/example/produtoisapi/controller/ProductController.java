package com.example.produtoisapi.controller;

import com.example.produtoisapi.model.Product;
import com.example.produtoisapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

    private  ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        System.out.println(product.getDescription());productRepository.save(product);
    }

    @GetMapping("{id}")
    public Product getProductByID(@PathVariable("id") String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @DeleteMapping("{id}")
    public void deleteProductByID(@PathVariable("id") String id) {
        productRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable("id") String id,@RequestBody Product product) {
        Product product1 = new Product();

        product1=productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        product1.setDescription(product.getDescription());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());

        productRepository.save(product1);
        return product1;
    }


}
