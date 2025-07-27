package com.example.arquiteturaspring.api;

import com.example.arquiteturaspring.motors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class TestFabricController {


    // Spring! Go to the container and give me a motor instance (object bean)
    @Autowired
    private Motor motor;

    @PostMapping
    public CarStatus turnOnCar(@RequestBody Key key){
        var car = new HondaHRV(motor);
        return car.Ignition(key);
    }

    @GetMapping("/desc/{brand}")
    public String GetCarDescription (@PathVariable("brand") String brand){
        if (brand.equals("HONDA")){
            HondaHRV hrv = new HondaHRV(motor);
            return hrv.getDescription();
        }
        if (brand.equals("TOYOTA")){
            Toyota toyota = new Toyota(motor);
            return toyota.getDescription();
        }
        return "This bran does not exist in my memory, sry!";

    }


}
