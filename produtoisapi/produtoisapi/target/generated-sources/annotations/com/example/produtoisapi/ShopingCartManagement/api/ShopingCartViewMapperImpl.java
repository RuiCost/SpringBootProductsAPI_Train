package com.example.produtoisapi.ShopingCartManagement.api;

import com.example.produtoisapi.ShopingCartManagement.model.ShopingCart;
import com.example.produtoisapi.productManagement.api.CategoryView;
import com.example.produtoisapi.productManagement.api.ProductView;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-09T14:55:44+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ShopingCartViewMapperImpl extends ShopingCartViewMapper {

    @Override
    public ShopingCartView toShopingCartView(ShopingCart product) {
        if ( product == null ) {
            return null;
        }

        ShopingCartView shopingCartView = new ShopingCartView();

        shopingCartView.setDate( product.getDate() );
        shopingCartView.setQuantity( product.getQuantity() );
        shopingCartView.setProduct( productToProductView( product.getProduct() ) );

        return shopingCartView;
    }

    @Override
    public Iterable<ShopingCartView> toShopingCartView(Iterable<ShopingCart> shopingCarts) {
        if ( shopingCarts == null ) {
            return null;
        }

        ArrayList<ShopingCartView> iterable = new ArrayList<ShopingCartView>();
        for ( ShopingCart shopingCart : shopingCarts ) {
            iterable.add( toShopingCartView( shopingCart ) );
        }

        return iterable;
    }

    @Override
    public List<ShopingCartView> toShopingCartView(List<ShopingCart> shopingCarts) {
        if ( shopingCarts == null ) {
            return null;
        }

        List<ShopingCartView> list = new ArrayList<ShopingCartView>( shopingCarts.size() );
        for ( ShopingCart shopingCart : shopingCarts ) {
            list.add( toShopingCartView( shopingCart ) );
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

    protected ProductView productToProductView(Product product) {
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
        productView.setImageURL( product.getImageURL() );

        return productView;
    }
}
