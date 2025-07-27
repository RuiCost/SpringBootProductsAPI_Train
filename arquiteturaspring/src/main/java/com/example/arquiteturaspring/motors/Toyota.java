package com.example.arquiteturaspring.motors;

public class Toyota extends Car {

    public Toyota(Motor motor) {
        super(motor);
        setBrand(Brand.TOYOTA);
    }

    @Override
    public String getDescription() {
        return " Known for being reliable, fuel-efficient, and easy to maintain. With a modern design, comfortable interiors, and smart safety features, they’re a great choice for everyday driving and long-term value. ";
    }

}
