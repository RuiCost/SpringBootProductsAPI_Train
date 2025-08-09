package com.example.produtoisapi.ProductInvoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.api.InvoiceView;
import com.example.produtoisapi.productManagement.api.ProductView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Product")
public class ProductInvoiceView {

 private ProductView product;

 private InvoiceView invoice;

 private Integer quantity;
}
