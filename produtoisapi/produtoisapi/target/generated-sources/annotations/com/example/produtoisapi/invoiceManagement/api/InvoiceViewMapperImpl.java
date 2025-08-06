package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.userManagement.api.UserView;
import com.example.produtoisapi.userManagement.model.Role;
import com.example.produtoisapi.userManagement.model.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-05T11:17:11+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class InvoiceViewMapperImpl extends InvoiceViewMapper {

    @Override
    public InvoiceView toInvoiceView(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceView invoiceView = new InvoiceView();

        invoiceView.setIdInvoice( invoice.getIdInvoice() );
        invoiceView.setStartDate( invoice.getStartDate() );
        invoiceView.setTotalPrice( invoice.getTotalPrice() );
        invoiceView.setPayMethod( invoice.getPayMethod() );
        invoiceView.setState( invoice.getState() );
        invoiceView.setUser( userToUserView( invoice.getUser() ) );

        return invoiceView;
    }

    @Override
    public Iterable<InvoiceView> toInvoiceView(Iterable<Invoice> invoices) {
        if ( invoices == null ) {
            return null;
        }

        ArrayList<InvoiceView> iterable = new ArrayList<InvoiceView>();
        for ( Invoice invoice : invoices ) {
            iterable.add( toInvoiceView( invoice ) );
        }

        return iterable;
    }

    protected UserView userToUserView(User user) {
        if ( user == null ) {
            return null;
        }

        UserView userView = new UserView();

        if ( user.getId() != null ) {
            userView.setId( String.valueOf( user.getId() ) );
        }
        userView.setUsername( user.getUsername() );
        userView.setFullName( user.getFullName() );
        Set<Role> set = user.getAuthorities();
        if ( set != null ) {
            userView.setAuthorities( new HashSet<Role>( set ) );
        }

        return userView;
    }
}
