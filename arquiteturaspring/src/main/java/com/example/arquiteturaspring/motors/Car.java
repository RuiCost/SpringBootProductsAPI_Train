package com.example.arquiteturaspring.motors;

import java.awt.*;

public class Car {

    private String model;
    private Color color;
    private Motor motor;
    private Brand brand;
    private String description;

    public Car(Motor motor) {
        this.motor = motor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public CarStatus Ignition(Key key) {
        if(key.getBrand()!=this.brand){
            return new CarStatus("This key doesn't have the same brand");
        }

        return new CarStatus("Carro ligado. Rodando com o motor " + motor);
    }

    public String getDescription() {
        return "A very generic car";
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
