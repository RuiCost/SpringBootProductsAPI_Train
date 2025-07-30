package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.productManagement.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductViewMapper {

    public abstract ProductView toProductView(Product product);

    public abstract Iterable<ProductView> toProductView(Iterable<Product> products);


}
