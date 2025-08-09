package com.example.produtoisapi.invoiceManagement.model;

import com.example.produtoisapi.userManagement.model.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
public class Invoice {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idInvoice;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private LocalDate Date =  LocalDate.now();

    @Min(0)
    private Double totalPrice=0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayMethod payMethod;



    public Invoice(User user, PayMethod payMethod) {
        this.user = user;
        this.payMethod = payMethod;
    }

    public Invoice() {

    }

    public Long getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Long idInvoice) {
        this.idInvoice = idInvoice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartDate() {
        return Date;
    }

    public void setStartDate(LocalDate startDate) {
        this.Date = startDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }


}
