package com.example.produtoisapi.ShopingCartManagement.services;
import com.example.produtoisapi.ShopingCartManagement.api.CreateShopingCartRequest;
import com.example.produtoisapi.ShopingCartManagement.model.ShopingCart;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.userManagement.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ShopingCartService {

    void deleteCartByUserId(Long userId);
    ShopingCart create(Long id,CreateShopingCartRequest resource);

    List<ShopingCart>  getCartByUser(Long id);
}
