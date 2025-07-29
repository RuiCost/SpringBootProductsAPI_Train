package com.example.produtoisapi.productManagement.services;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.userManagement.model.User;

import java.util.List;
import java.util.Optional;

public interface ProductService {


    Product create(CreateProductRequest resource);

}
