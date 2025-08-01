package com.example.produtoisapi.productManagement.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotNull
    @NotBlank
    private String name;

    @Min(0)
    private Double price;


    @NotNull
    private String description;

    @NotNull
    private Long category;
}
