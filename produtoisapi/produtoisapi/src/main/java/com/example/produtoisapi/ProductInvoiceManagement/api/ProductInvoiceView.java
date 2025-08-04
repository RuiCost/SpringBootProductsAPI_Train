package com.example.produtoisapi.ProductInvoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.api.InvoiceView;
import com.example.produtoisapi.invoiceManagement.model.PayMethod;
import com.example.produtoisapi.invoiceManagement.model.State;
import com.example.produtoisapi.productManagement.api.ProductView;
import com.example.produtoisapi.userManagement.api.UserView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Product")
public class ProductInvoiceView {

 private ProductView product;

 private InvoiceView invoice;

 private Double quantity;
}
