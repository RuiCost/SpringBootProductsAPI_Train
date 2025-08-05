package com.example.produtoisapi.productManagement.api;

import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T11:17:07+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ProductViewMapperImpl extends ProductViewMapper {

    @Override
    public ProductView toProductView(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductView productView = new ProductView();

        productView.setId( product.getId() );
        productView.setName( product.getName() );
        productView.setPrice( product.getPrice() );
        productView.setQuantity( product.getQuantity() );
        productView.setDescription( product.getDescription() );
        productView.setCategory( categoryToCategoryView( product.getCategory() ) );

        return productView;
    }

    @Override
    public Iterable<ProductView> toProductView(Iterable<Product> products) {
        if ( products == null ) {
            return null;
        }

        ArrayList<ProductView> iterable = new ArrayList<ProductView>();
        for ( Product product : products ) {
            iterable.add( toProductView( product ) );
        }

        return iterable;
    }

    @Override
    public List<ProductView> toProductView(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductView> list = new ArrayList<ProductView>( products.size() );
        for ( Product product : products ) {
            list.add( toProductView( product ) );
        }

        return list;
    }

    protected CategoryView categoryToCategoryView(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryView categoryView = new CategoryView();

        categoryView.setIdCategory( category.getIdCategory() );
        categoryView.setName( category.getName() );

        return categoryView;
    }
}
