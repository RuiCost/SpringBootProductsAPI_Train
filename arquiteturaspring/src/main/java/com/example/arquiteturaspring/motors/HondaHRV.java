package com.example.arquiteturaspring.motors;

import java.awt.*;

public class HondaHRV extends Car {


    public HondaHRV(Motor motor) {
        super(motor);
        setModel("HRV");
        setColor(Color.BLACK);
        setBrand(Brand.HONDA);
    }



}
