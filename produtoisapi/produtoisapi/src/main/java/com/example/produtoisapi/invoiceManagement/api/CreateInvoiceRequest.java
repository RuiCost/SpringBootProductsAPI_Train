package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.model.PayMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {

    private Long user;

    private PayMethod payMethod;

}
