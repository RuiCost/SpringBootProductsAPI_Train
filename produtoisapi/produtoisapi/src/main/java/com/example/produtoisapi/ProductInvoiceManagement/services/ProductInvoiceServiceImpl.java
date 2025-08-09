package com.example.produtoisapi.ProductInvoiceManagement.services;

import com.example.produtoisapi.ProductInvoiceManagement.api.CreateProductInvoiceRequest;
import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.ProductInvoiceManagement.repositories.ProductInvoiceRepository;
import com.example.produtoisapi.exceptions.NotFoundException;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.invoiceManagement.repositories.InvoiceRepository;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInvoiceServiceImpl implements ProductInvoiceService {

    private final ProductInvoiceRepository repository;

    private final ProductRepository productRepository;

    private final EditProductInvoiceMapper editProductMapper;

    private final InvoiceRepository invoiceRepository;


    @Override
    public ProductInvoice create(CreateProductInvoiceRequest resource) {

        final var invoice1 = invoiceRepository.findById(resource.getIdInvoice())
                .orElseThrow(() -> new NotFoundException("This Invoice does not exist"));


        final var product = productRepository.findById(resource.getIdProduct())
                .orElseThrow(() -> new NotFoundException("This product does not exist"));



        if (resource.getQuantity() > product.getQuantity()){
            throw new IllegalArgumentException("Their is less stock available for product "+ product.getName() + " please confirm that their is still some available and if not remove from shop cart");
        }

        // Calculate new total price of product
        invoice1.setTotalPrice(invoice1.getTotalPrice()+ resource.getQuantity()* product.getPrice());

        //Reduce stock
        product.setQuantity(product.getQuantity()-resource.getQuantity());

        productRepository.save(product);

        if (resource.getQuantity() == null || resource.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        final var existing = repository.findByInvoiceIdAndProductId(resource.getIdInvoice(), resource.getIdProduct());

        if (existing.isPresent()) {
            // Se já existe, atualizamos a quantidade
            final var invoiceProduct = existing.get();
            Integer newQuantity = invoiceProduct.getQuantity() + resource.getQuantity();
            invoiceProduct.setQuantity(newQuantity);
            return repository.save(invoiceProduct);
        }

        // Se não existe, criamos normalmente
        final ProductInvoice productInvoice = editProductMapper.create(resource);
        return repository.save(productInvoice);
    }


    @Override
    public Iterable<Invoice> findAll(int page, Integer size) {
        return null;
    }
}
