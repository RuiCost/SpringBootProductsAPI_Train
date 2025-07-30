package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CategoryViewMapper {

    public abstract CategoryView toCategoryView(Category category);

    public abstract Iterable<CategoryView> toCategoryView(Iterable<Category> categories);


}
