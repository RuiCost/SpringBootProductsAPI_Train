package com.example.produtoisapi.ProductInvoiceManagement.services;

import com.example.produtoisapi.ProductInvoiceManagement.api.CreateProductInvoiceRequest;
import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.ProductInvoiceManagement.repositories.ProductInvoiceRepository;
import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.invoiceManagement.model.State;
import com.example.produtoisapi.invoiceManagement.repositories.InvoiceRepository;
import com.example.produtoisapi.invoiceManagement.services.EditInvoiceMapper;
import com.example.produtoisapi.invoiceManagement.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInvoiceServiceImpl implements ProductInvoiceService {

    private final ProductInvoiceRepository repository;

    private final EditProductInvoiceMapper editProductMapper;


    @Override
    public ProductInvoice create(CreateProductInvoiceRequest resource) {
        final ProductInvoice invoice = editProductMapper.create(resource);
        return repository.save(invoice);
    }

    @Override
    public Iterable<Invoice> findAll(int page, Integer size) {
        return null;
    }
}
