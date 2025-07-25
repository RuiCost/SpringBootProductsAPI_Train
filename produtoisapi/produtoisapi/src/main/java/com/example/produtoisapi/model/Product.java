package com.example.produtoisapi.model;


import jakarta.persistence.*;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@Table
public class Product {

    // UUIDs are used to assign truly random IDs, with the probability of generating a duplicate being so low that it’s more likely for the sun to explode first.
    // BUT if u like more organized and for controled APIs then u can just use "GenerationType.AUTO" that uses type like int or long...

    @Id
    @Column(name = "id")
    private String id=UUID.randomUUID().toString();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String idPlan) {
        this.id = idPlan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
