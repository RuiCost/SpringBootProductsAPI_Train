package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.api.CategoryViewMapper;
import com.example.produtoisapi.productManagement.api.ProductViewMapper;
import com.example.produtoisapi.productManagement.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryViewMapper.class})
public interface ProductInvoiceMapper {

    @Mapping(source = "product.id", target = "id")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "product.description", target = "description")
    @Mapping(source = "product.category", target = "category")
    @Mapping(source = "quantity", target = "quantity")
    ProductInInvoiceView toView(ProductInvoice productInvoice);

    List<ProductInInvoiceView> toViewList(List<ProductInvoice> productInvoices);
}
