package com.example.produtoisapi.invoiceManagement.services;

import com.example.produtoisapi.invoiceManagement.api.CreateInvoiceRequest;
import com.example.produtoisapi.invoiceManagement.model.Invoice;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T11:17:07+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class EditInvoiceMapperImpl extends EditInvoiceMapper {

    @Override
    public Invoice create(CreateInvoiceRequest request) {
        if ( request == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setUser( toUser( request.getUser() ) );
        invoice.setPayMethod( request.getPayMethod() );

        return invoice;
    }
}
