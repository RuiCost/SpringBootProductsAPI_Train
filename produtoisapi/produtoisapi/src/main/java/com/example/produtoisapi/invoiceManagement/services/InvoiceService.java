package com.example.produtoisapi.invoiceManagement.services;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.userManagement.model.User;

import java.util.Optional;

public interface InvoiceService {

    Invoice create(CreateInvoiceRequest resource);

    Iterable<Invoice> findAll(int page, Integer size);


}
