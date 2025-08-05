package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.api.ProductView;
import com.example.produtoisapi.productManagement.api.ProductViewMapper;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.userManagement.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductViewMapper.class)
public abstract class InvoiceViewMapper {

    public abstract InvoiceView toInvoiceView(Invoice invoice);

    public abstract Iterable<InvoiceView> toInvoiceView(Iterable<Invoice> invoices);



}

