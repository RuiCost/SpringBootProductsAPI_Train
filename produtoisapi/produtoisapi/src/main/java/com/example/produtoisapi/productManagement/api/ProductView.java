package com.example.produtoisapi.productManagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Schema(description = "Product")
public class ProductView {

    private String id;

    private String name;

    private Double price;

    private Integer quantity;

    private String description;

    private CategoryView Category;

    private String imageURL;

}
