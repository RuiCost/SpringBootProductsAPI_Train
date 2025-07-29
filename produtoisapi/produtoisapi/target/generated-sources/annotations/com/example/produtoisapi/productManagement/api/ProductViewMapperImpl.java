package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.productManagement.model.Product;
import java.util.ArrayList;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-29T16:12:34+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ProductViewMapperImpl extends ProductViewMapper {

    @Override
    public ProductView toPlanView(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductView productView = new ProductView();

        productView.setId( product.getId() );
        productView.setName( product.getName() );
        productView.setPrice( product.getPrice() );
        productView.setDescription( product.getDescription() );

        return productView;
    }

    @Override
    public Iterable<ProductView> toPlanView(Iterable<Product> products) {
        if ( products == null ) {
            return null;
        }

        ArrayList<ProductView> iterable = new ArrayList<ProductView>();
        for ( Product product : products ) {
            iterable.add( toPlanView( product ) );
        }

        return iterable;
    }
}
