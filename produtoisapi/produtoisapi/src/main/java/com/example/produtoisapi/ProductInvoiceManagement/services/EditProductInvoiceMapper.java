package com.example.produtoisapi.ProductInvoiceManagement.services;

import com.example.produtoisapi.ProductInvoiceManagement.api.CreateProductInvoiceRequest;
import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.invoiceManagement.repositories.InvoiceRepository;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.userManagement.model.User;
import com.example.produtoisapi.userManagement.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ValidationException;
@Mapper(componentModel = "spring")
public abstract class EditProductInvoiceMapper {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Product toProduct(final String idProduct) {
        return productRepository.findById(idProduct)
                .orElseThrow(() -> new ValidationException("Select an existing product"));
    }

    public Invoice toInvoice(final Long idInvoice) {
        return invoiceRepository.findById(idInvoice)
                .orElseThrow(() -> new ValidationException("Select an existing invoice"));
    }


    public ProductInvoice create(CreateProductInvoiceRequest request) {
        Product product = toProduct(request.getIdProduct());
        Invoice invoice = toInvoice(request.getIdInvoice());
        Double quantity = request.getQuantity();

        return new ProductInvoice(product, invoice, quantity);
    }
}

