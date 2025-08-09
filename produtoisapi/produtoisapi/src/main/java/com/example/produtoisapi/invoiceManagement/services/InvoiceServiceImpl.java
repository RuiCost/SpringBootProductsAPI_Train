package com.example.produtoisapi.invoiceManagement.services;

import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.ProductInvoiceManagement.repositories.ProductInvoiceRepository;
import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.invoiceManagement.repositories.InvoiceRepository;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final ProductRepository productRepository;

    private final ProductInvoiceRepository productInvoiceRepository;

    private final InvoiceRepository repository;

    private final EditInvoiceMapper editProductMapper;



    @Override
    public Invoice create(CreateInvoiceRequest resource) {
        final Invoice invoice = editProductMapper.create(resource);
        return repository.save(invoice);


    }

    public List<ProductInvoice> getAllProductInvoicesOfInvoice(Long id) {
        final var invoice = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));

        return productInvoiceRepository.findAllByInvoiceId(id);
    }



    @Override
    public Iterable<Invoice> findAll(int page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAllProducts(pageable);
    }



}
