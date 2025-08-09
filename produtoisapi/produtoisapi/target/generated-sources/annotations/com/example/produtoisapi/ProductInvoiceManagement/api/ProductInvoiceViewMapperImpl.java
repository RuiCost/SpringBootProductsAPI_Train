package com.example.produtoisapi.ProductInvoiceManagement.api;

import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.invoiceManagement.api.InvoiceView;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.api.CategoryView;
import com.example.produtoisapi.productManagement.api.ProductView;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.userManagement.api.UserView;
import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-08T16:32:20+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ProductInvoiceViewMapperImpl extends ProductInvoiceViewMapper {

    @Override
    public ProductInvoiceView toProductInvoiceView(ProductInvoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        ProductInvoiceView productInvoiceView = new ProductInvoiceView();

        productInvoiceView.setProduct( productToProductView( invoice.getProduct() ) );
        productInvoiceView.setInvoice( invoiceToInvoiceView( invoice.getInvoice() ) );
        productInvoiceView.setQuantity( invoice.getQuantity() );

        return productInvoiceView;
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

    protected UserView userToUserView(User user) {
        if ( user == null ) {
            return null;
        }

        UserView userView = new UserView();

        if ( user.getId() != null ) {
            userView.setId( String.valueOf( user.getId() ) );
        }
        userView.setUsername( user.getUsername() );
        userView.setFullName( user.getFullName() );
        Set<Role> set = user.getAuthorities();
        if ( set != null ) {
            userView.setAuthorities( new HashSet<Role>( set ) );
        }

        return userView;
    }

    protected InvoiceView invoiceToInvoiceView(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceView invoiceView = new InvoiceView();

        invoiceView.setIdInvoice( invoice.getIdInvoice() );
        invoiceView.setStartDate( invoice.getStartDate() );
        invoiceView.setTotalPrice( invoice.getTotalPrice() );
        invoiceView.setPayMethod( invoice.getPayMethod() );
        invoiceView.setUser( userToUserView( invoice.getUser() ) );

        return invoiceView;
    }
}
