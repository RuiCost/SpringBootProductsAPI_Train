package com.example.produtoisapi.ShopingCartManagement.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShopingCartRequest {


    private String idProduct;


    @Min(0)
    private Integer quantity;


}
