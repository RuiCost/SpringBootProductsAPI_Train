package com.example.produtoisapi.productManagement.model;
import org.hibernate.StaleObjectStateException;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Product {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, unique = true, updatable = false)
    @NotBlank
    @NotNull
    private String name;

    @Column(nullable = false)
    @Min(0)
    private Double price;

    @Column()
    @NotNull
    private String description;


    @Version
    private long version;
    protected Product() {
    }
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getVersion() {
        return version;
    }
}
