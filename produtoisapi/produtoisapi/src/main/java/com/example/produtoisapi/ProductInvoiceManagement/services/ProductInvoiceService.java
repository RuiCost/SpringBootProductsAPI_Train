package com.example.produtoisapi.ProductInvoiceManagement.services;
import com.example.produtoisapi.ProductInvoiceManagement.api.CreateProductInvoiceRequest;
import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;

public interface ProductInvoiceService {

    ProductInvoice create(CreateProductInvoiceRequest resource);

    Iterable<Invoice> findAll(int page, Integer size);



}
