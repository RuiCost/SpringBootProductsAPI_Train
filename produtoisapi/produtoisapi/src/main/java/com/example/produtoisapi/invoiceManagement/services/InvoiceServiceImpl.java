package com.example.produtoisapi.invoiceManagement.services;

import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.invoiceManagement.repositories.InvoiceRepository;
import com.example.produtoisapi.productManagement.api.CreateProductRequest;
import com.example.produtoisapi.productManagement.api.EditProductRequest;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.productManagement.services.EditProductMapper;
import com.example.produtoisapi.userManagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repository;

    private final EditInvoiceMapper editProductMapper;



    @Override
    public Invoice create(CreateInvoiceRequest resource) {
        final Invoice invoice = editProductMapper.create(resource);
        return repository.save(invoice);


    }

    @Override
    public Iterable<Invoice> findAll(int page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllProducts(pageable);
    }
}
