package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.productManagement.api.ProductView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Schema(description = "Products")
public class ProductsOfInvoiceView {
    private List<ProductInInvoiceView> products=new ArrayList<>();
}
