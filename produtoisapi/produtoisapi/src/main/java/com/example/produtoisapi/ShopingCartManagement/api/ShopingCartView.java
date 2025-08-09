package com.example.produtoisapi.ShopingCartManagement.api;

import com.example.produtoisapi.productManagement.api.CategoryView;
import com.example.produtoisapi.productManagement.api.ProductView;
import com.example.produtoisapi.userManagement.api.UserView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "ShopingCarts")
public class ShopingCartView {



    private LocalDate date;

    private Integer quantity;


    private ProductView product;
}
