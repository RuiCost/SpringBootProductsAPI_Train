package com.example.produtoisapi.ProductInvoiceManagement.repositories;
import com.example.produtoisapi.ProductInvoiceManagement.model.ProductInvoice;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInvoiceRepository extends CrudRepository<ProductInvoice,Long> {


    @Query("SELECT pi FROM ProductInvoice pi WHERE pi.invoice.idInvoice = :invoiceId")
    List<ProductInvoice> findAllByInvoiceId(@Param("invoiceId") Long invoiceId);

}
