package com.example.produtoisapi.invoiceManagement.api;

import com.example.produtoisapi.invoiceManagement.model.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditInvoiceRequest {
    private State state;
}


