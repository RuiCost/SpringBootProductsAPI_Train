package com.example.produtoisapi.invoiceManagement.repositories;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice,Long> {


    @Query("SELECT p FROM Invoice p ORDER BY p.Date ASC")
    Page<Invoice> findAllProducts(Pageable pageable);

}
