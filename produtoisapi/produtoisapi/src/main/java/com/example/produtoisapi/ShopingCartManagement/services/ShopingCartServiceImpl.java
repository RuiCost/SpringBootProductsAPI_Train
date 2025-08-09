package com.example.produtoisapi.ShopingCartManagement.services;

import com.example.produtoisapi.ShopingCartManagement.api.CreateShopingCartRequest;
import com.example.produtoisapi.ShopingCartManagement.model.ShopingCart;
import com.example.produtoisapi.ShopingCartManagement.repositories.ShopingCartRepository;
import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopingCartServiceImpl implements ShopingCartService {

    private final ShopingCartRepository repository;

    private final EditShopingCartMapper editShopingCartMapper;

    private final UserRepository userRepository;

    @Override
    public ShopingCart create(Long userId, CreateShopingCartRequest resource) {
        final ShopingCart shopingCart = editShopingCartMapper.create(userId, resource);
        return repository.save(shopingCart);
    }


    @Override
    public List<ShopingCart> getCartByUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return repository.findAllByUser(user);
    }

    @Override
    public void deleteCartByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        repository.deleteAllByUser(user);
    }


}
