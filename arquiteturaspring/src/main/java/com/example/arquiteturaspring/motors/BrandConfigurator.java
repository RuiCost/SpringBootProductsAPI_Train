package com.example.arquiteturaspring.motors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandConfigurator {

    // When you create a method that returns a constructed object, and when the object is constructed, it returns a bean
    //
    @Bean
    public Motor motor() {
        var motor = new Motor();
        motor.setHorsePower(120);
        motor.setCylinder(4);
        motor.setModelo("XPTO-0");
        motor.setLiquid(2.0);
        motor.setMotorType(MotorType.ASPIRADO);
        return motor;
    }

    @Bean
    public Motor motor1() {
        var motor = new Motor();
        motor.setHorsePower(300);
        motor.setCylinder(9);
        motor.setModelo("XPTO-1221");
        motor.setLiquid(100.0);
        motor.setMotorType(MotorType.TURBO);
        return motor;
    }

}
