package com.example.produtoisapi.ShopingCartManagement.api;

import com.example.produtoisapi.ShopingCartManagement.model.ShopingCart;
import com.example.produtoisapi.productManagement.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ShopingCartViewMapper {

    public abstract ShopingCartView toShopingCartView(ShopingCart product);

    public abstract Iterable<ShopingCartView> toShopingCartView(Iterable<ShopingCart> shopingCarts);

    public abstract List<ShopingCartView> toShopingCartView(List<ShopingCart> shopingCarts);

}
