package com.example.produtoisapi.invoiceManagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Product information in an invoice")
public class ProductHistoryView {

    private String idProduct;
    private String name;
    private Double price;
    private Integer quantity;
}
