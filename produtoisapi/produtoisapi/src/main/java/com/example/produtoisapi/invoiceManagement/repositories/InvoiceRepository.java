package com.example.produtoisapi.invoiceManagement.repositories;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends CrudRepository<Invoice,Long> {


    @Query("SELECT p FROM Invoice p ORDER BY p.Date ASC")
    Page<Invoice> findAllProducts(Pageable pageable);

    @Query("SELECT i FROM Invoice i WHERE i.user.id = :userId ORDER BY i.Date DESC")
    List<Invoice> findAllInvoicesOfUser(@Param("userId") Long userId);
    @Query("SELECT DISTINCT i FROM Invoice i " +
            "JOIN i.products pi " +
            "JOIN pi.product p " +
            "WHERE i.user.id = :userId " +
            "ORDER BY i.Date DESC")
    List<Invoice> findAllInvoicesWithProductsByUserId(@Param("userId") Long userId);


}
