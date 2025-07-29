package com.example.produtoisapi.productManagement.services;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;

@Mapper(componentModel = "spring")
public abstract class EditProductMapper {

    public abstract Product create(CreateProductRequest request);

    public abstract void update(EditProductRequest request, @MappingTarget Product product);
}