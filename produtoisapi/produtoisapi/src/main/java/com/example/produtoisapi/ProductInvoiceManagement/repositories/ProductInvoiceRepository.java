package com.example.produtoisapi.ProductInvoiceManagement.repositories;
import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductInvoiceRepository extends CrudRepository<ProductInvoice,Long> {



}
