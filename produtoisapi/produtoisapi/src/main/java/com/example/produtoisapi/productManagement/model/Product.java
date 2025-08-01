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

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

    @Version
    private long version;
    protected Product() {
    }
    public Product(String name, Double price, Category category) {
        this.name = name;
        this.price = price;
        this.category=category;
    }

    public void applyPatch(final long desiredVersion, final double price, final String description){
        if (this.version != desiredVersion) {
            throw new StaleObjectStateException("Object was already modified by another user", this.id);
        }
        setPrice(price);
        setDescription(description);
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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null, nor blank");
        }
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price<0) {
            throw new IllegalArgumentException("Price cannot be under 0 euros!");
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must not be null, nor blank");
        }
        this.description = description;
    }

    public long getVersion() {
        return version;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
