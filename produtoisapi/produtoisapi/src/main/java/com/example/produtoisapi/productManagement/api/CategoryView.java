package com.example.produtoisapi.productManagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Category")
public class CategoryView {

    private Long id;
    private String name;

}
