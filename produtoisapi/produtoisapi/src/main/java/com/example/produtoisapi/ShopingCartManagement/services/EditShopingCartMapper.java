package com.example.produtoisapi.ShopingCartManagement.services;

import com.example.produtoisapi.ShopingCartManagement.api.CreateShopingCartRequest;
import com.example.produtoisapi.ShopingCartManagement.model.ShopingCart;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.CategoryRepository;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;
import java.time.LocalDate;

@Mapper(componentModel = "spring")
public abstract class EditShopingCartMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public ShopingCart create(Long userId,CreateShopingCartRequest request) {
        User user = toUser(userId);
        Product product = toProduct(request.getIdProduct());

        return new ShopingCart(
                user,
                product,
                request.getQuantity()
        );
    }

    public Product toProduct(final String idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new ValidationException("Select an existing product"));
    }

    public User toUser(final Long idUser) {
        return userRepository.findById(idUser)
                .orElseThrow(() -> new ValidationException("Select an existing user"));
    }
}
