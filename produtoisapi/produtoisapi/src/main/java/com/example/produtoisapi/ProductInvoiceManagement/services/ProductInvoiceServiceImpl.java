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

        if (resource.getQuantity() == null || resource.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        final var existing = repository.findByInvoiceIdAndProductId(resource.getIdInvoice(), resource.getIdProduct());

        if (existing.isPresent()) {
            // Se já existe, atualizamos a quantidade
            final var invoiceProduct = existing.get();
            double newQuantity = invoiceProduct.getQuantity() + resource.getQuantity();
            invoiceProduct.setQuantity(newQuantity);
            return repository.save(invoiceProduct);
        }

        // Se não existe, criamos normalmente
        final ProductInvoice invoice = editProductMapper.create(resource);
        return repository.save(invoice);
    }


    @Override
    public Iterable<Invoice> findAll(int page, Integer size) {
        return null;
    }
}
