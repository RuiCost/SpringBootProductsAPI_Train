package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.model.PayMethod;
import com.example.produtoisapi.userManagement.api.UserView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "Invoice History with products bought")
public class InvoiceHistoryView {

    private Long idInvoice;

    private LocalDate startDate;

    private Double totalPrice;

    private PayMethod payMethod;


    private List<ProductHistoryView> products;
}
