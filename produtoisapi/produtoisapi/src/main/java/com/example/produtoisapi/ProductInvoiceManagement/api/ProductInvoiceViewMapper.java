package com.example.produtoisapi.ProductInvoiceManagement.api;

import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductInvoiceViewMapper {


    public abstract ProductInvoiceView toProductInvoiceView(ProductInvoice invoice);

   // public abstract Iterable<ProductInvoiceView> toProductInvoiceView(Iterable<ProductInvoice> invoices);



}
