package com.example.produtoisapi.ShopingCartManagement.api;


import com.example.produtoisapi.ShopingCartManagement.model.ShopingCart;
import com.example.produtoisapi.ShopingCartManagement.services.ShopingCartService;
import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.ProductView;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.services.ProductService;
import com.example.produtoisapi.userManagement.api.UploadFileResponse;
import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.repositories.UserRepository;
import com.example.produtoisapi.userManagement.services.FileStorageService;
import com.example.produtoisapi.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="Products",description = "Products Endpoints")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopingCart")
public class ShopingCartCrontroller {

    private final ShopingCartService shopingCartService;
    private final ShopingCartViewMapper shopingCartViewMapper;

    @Autowired
    private final Utils utils;

    @PatchMapping(value = "multiple")
    public ResponseEntity<List<ShopingCartView>> addToShopCartMultipleProducts(
            HttpServletRequest request,
            @Valid @RequestBody final List<@Valid CreateShopingCartRequest> resources
    ) {
        Long userId = utils.getUserByToken(request);

        shopingCartService.deleteCartByUserId(userId);

        if (resources.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        final var shoppingCarts = resources.stream()
                .map(r -> shopingCartService.create(userId, r))
                .map(shopingCartViewMapper::toShopingCartView)
                .collect(Collectors.toList());

        return ResponseEntity.ok(shoppingCarts);
    }


    @PostMapping
    public ResponseEntity<ShopingCartView> create(HttpServletRequest request,@Valid @RequestBody final CreateShopingCartRequest resource) {

        Long userId = utils.getUserByToken(request);
        final var shopingCart = shopingCartService.create(userId,resource);
        // for version control:
        final var newDeviceUri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(shopingCart.getIdShopingCart().toString())
                .build().toUri();

        return ResponseEntity.created(newDeviceUri).body(shopingCartViewMapper.toShopingCartView(shopingCart));
    }


    @GetMapping()
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    public ResponseEntity<List<ShopingCartView>> getCart(HttpServletRequest request) {
        Long userId = utils.getUserByToken(request);
        List<ShopingCart> cart = shopingCartService.getCartByUser(userId);
        return ResponseEntity.ok(shopingCartViewMapper.toShopingCartView(cart));
    }



}
