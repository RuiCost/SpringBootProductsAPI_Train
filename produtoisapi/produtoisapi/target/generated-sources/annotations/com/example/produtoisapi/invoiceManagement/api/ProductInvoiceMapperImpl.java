package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.productManagement.api.CategoryViewMapper;
import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T11:17:07+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class ProductInvoiceMapperImpl implements ProductInvoiceMapper {

    @Autowired
    private CategoryViewMapper categoryViewMapper;

    @Override
    public ProductInInvoiceView toView(ProductInvoice productInvoice) {
        if ( productInvoice == null ) {
            return null;
        }

        ProductInInvoiceView productInInvoiceView = new ProductInInvoiceView();

        productInInvoiceView.setId( productInvoiceProductId( productInvoice ) );
        productInInvoiceView.setName( productInvoiceProductName( productInvoice ) );
        productInInvoiceView.setPrice( productInvoiceProductPrice( productInvoice ) );
        productInInvoiceView.setDescription( productInvoiceProductDescription( productInvoice ) );
        productInInvoiceView.setCategory( categoryViewMapper.toCategoryView( productInvoiceProductCategory( productInvoice ) ) );
        productInInvoiceView.setQuantity( productInvoice.getQuantity() );

        return productInInvoiceView;
    }

    @Override
    public List<ProductInInvoiceView> toViewList(List<ProductInvoice> productInvoices) {
        if ( productInvoices == null ) {
            return null;
        }

        List<ProductInInvoiceView> list = new ArrayList<ProductInInvoiceView>( productInvoices.size() );
        for ( ProductInvoice productInvoice : productInvoices ) {
            list.add( toView( productInvoice ) );
        }

        return list;
    }

    private String productInvoiceProductId(ProductInvoice productInvoice) {
        if ( productInvoice == null ) {
            return null;
        }
        Product product = productInvoice.getProduct();
        if ( product == null ) {
            return null;
        }
        String id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productInvoiceProductName(ProductInvoice productInvoice) {
        if ( productInvoice == null ) {
            return null;
        }
        Product product = productInvoice.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Double productInvoiceProductPrice(ProductInvoice productInvoice) {
        if ( productInvoice == null ) {
            return null;
        }
        Product product = productInvoice.getProduct();
        if ( product == null ) {
            return null;
        }
        Double price = product.getPrice();
        if ( price == null ) {
            return null;
        }
        return price;
    }

    private String productInvoiceProductDescription(ProductInvoice productInvoice) {
        if ( productInvoice == null ) {
            return null;
        }
        Product product = productInvoice.getProduct();
        if ( product == null ) {
            return null;
        }
        String description = product.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private Category productInvoiceProductCategory(ProductInvoice productInvoice) {
        if ( productInvoice == null ) {
            return null;
        }
        Product product = productInvoice.getProduct();
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        return category;
    }
}
