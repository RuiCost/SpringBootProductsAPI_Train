package com.example.produtoisapi.productManagement.services;

import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-01T14:53:13+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class EditProductMapperImpl extends EditProductMapper {

    @Override
    public Product create(CreateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        String name = null;
        Double price = null;
        Category category = null;

        name = request.getName();
        price = request.getPrice();
        category = toCategory( request.getCategory() );

        Product product = new Product( name, price, category );

        product.setDescription( request.getDescription() );

        return product;
    }
}
