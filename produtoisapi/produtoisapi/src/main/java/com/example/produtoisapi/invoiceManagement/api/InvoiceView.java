package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.model.PayMethod;
import com.example.produtoisapi.invoiceManagement.model.State;
import com.example.produtoisapi.productManagement.api.CategoryView;
import com.example.produtoisapi.productManagement.api.ProductView;
import com.example.produtoisapi.userManagement.api.UserView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Product")
public class InvoiceView {

    private Long idInvoice;

    private LocalDate startDate;

    private Double totalPrice;

    private PayMethod payMethod;

    private State state;
    // This is not mandatory u coud just show for example the id or name of user etc...
    // But for this project we will show all info of user that is related to the invoice that needs to be seen (not recommended)
    private UserView User;

}
