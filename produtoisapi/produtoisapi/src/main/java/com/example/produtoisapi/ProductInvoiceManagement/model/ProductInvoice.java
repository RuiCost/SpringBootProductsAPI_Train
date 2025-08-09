package com.example.produtoisapi.ProductInvoiceManagement.model;

import com.example.produtoisapi.invoiceManagement.model.Invoice;
import com.example.produtoisapi.productManagement.model.Product;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class ProductInvoice {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProductInvoice;

    @ManyToOne
    @JoinColumn(name = "idProduct",nullable = false)
    private Product product;


    @ManyToOne(optional = false)
    @JoinColumn(name = "idInvoice",nullable = false)
    private Invoice invoice;


    @Column(nullable = false)
    @Min(0)
    private Integer quantity;

    public ProductInvoice(Product product, Invoice invoice, Integer quantity) {
        this.product = product;
        this.invoice = invoice;
        this.quantity = quantity;
    }

    public ProductInvoice() {

    }

    public Long getIdProductInvoice() {
        return idProductInvoice;
    }

    public void setIdProductInvoice(Long idProductInvoice) {
        this.idProductInvoice = idProductInvoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
