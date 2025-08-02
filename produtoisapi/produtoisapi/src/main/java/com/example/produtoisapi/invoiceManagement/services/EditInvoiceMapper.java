package com.example.produtoisapi.invoiceManagement.services;

import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.CategoryRepository;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;

@Mapper(componentModel = "spring")
public abstract class EditInvoiceMapper {


    @Autowired
    private UserRepository userRepository;
    public abstract Invoice create(CreateInvoiceRequest request);

    public User toUser (final Long idUser){
        return userRepository.findById(idUser).orElseThrow(() -> new ValidationException("Select an existing user1"));
    }


}