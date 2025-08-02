package com.example.produtoisapi.productManagement.services;

import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.repositories.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;

@Mapper(componentModel = "spring")
public abstract class EditProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public abstract Product create(CreateProductRequest request);

    // To also be able to show all info of the category along with product:
    // To be able to see the info of catergory(id, name)  we  need to get the info associated to a category id
   //Thus,...::
    public Category toCategory (final Long idCategory){
        return categoryRepository.findById(idCategory).orElseThrow(() -> new ValidationException("Select an existing category"));
    }


}