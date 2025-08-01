package com.example.produtoisapi.productManagement.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditProductRequest {
    @Min(0)
    private Double price;

    @Min(0)
    private Integer quantity;

    private String description;


}
