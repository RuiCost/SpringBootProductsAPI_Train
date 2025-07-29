package com.example.produtoisapi.productManagement.services;

import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T16:12:34+0100",
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

        name = request.getName();
        price = request.getPrice();

        Product product = new Product( name, price );

        product.setDescription( request.getDescription() );

        return product;
    }

    @Override
    public void update(EditProductRequest request, Product product) {
        if ( request == null ) {
            return;
        }

        product.setPrice( request.getPrice() );
        product.setDescription( request.getDescription() );
    }
}
