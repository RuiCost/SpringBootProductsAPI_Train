package com.example.produtoisapi.ProductInvoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.model.PayMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductInvoiceRequest {


    private String idProduct;

    private Long idInvoice;

    private Double quantity;

}
