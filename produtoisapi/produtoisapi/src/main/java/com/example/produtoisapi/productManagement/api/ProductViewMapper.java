package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.productManagement.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductViewMapper {

    public abstract ProductView toPlanView(Product product);

    public abstract Iterable<ProductView> toPlanView(Iterable<Product> products);


}
