package com.example.produtoisapi.invoiceManagement.services;
import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.userManagement.model.User;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    Invoice create(CreateInvoiceRequest resource);

    List<ProductInvoice> getAllProductInvoicesOfInvoice(Long id);
    Iterable<Invoice> findAll(int page, Integer size);

    Invoice changeStateOfInvoicetoPaid(Long id);



    Invoice changeStateOfInvoicetoCANCEL(Long id);


    Invoice changeStateOfInvoicetoPENDING( Long id);



}
