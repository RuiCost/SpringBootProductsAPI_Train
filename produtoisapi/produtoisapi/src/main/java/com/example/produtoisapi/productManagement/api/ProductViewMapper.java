package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.productManagement.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductViewMapper {

    public abstract ProductView toProductView(Product product);

    public abstract Iterable<ProductView> toProductView(Iterable<Product> products);

    public abstract List<ProductView> toProductView(List<Product> products);

}
