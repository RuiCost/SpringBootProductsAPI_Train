package com.example.produtoisapi.ShopingCartManagement.model;

import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.userManagement.model.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class ShopingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idShopingCart;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idProduct",nullable = false)
    private Product product;
    @Column(nullable = false)
    @Min(0)
    private Integer quantity;
    @Column(nullable = false)
    private LocalDate date =  LocalDate.now();

    public ShopingCart(User user, Product product, Integer quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public ShopingCart() {

    }

    public Long getIdShopingCart() {
        return idShopingCart;
    }

    public void setIdShopingCart(Long idShopingCart) {
        this.idShopingCart = idShopingCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        date = date;
    }
}
