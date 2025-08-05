package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.productManagement.api.CategoryView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Product in Invoice")
public class ProductInInvoiceView {
    private String id;
    private String name;
    private Double price;
    private String description;
    private CategoryView category;

    private Double quantity; // <- Vem da ProductInvoice
}
