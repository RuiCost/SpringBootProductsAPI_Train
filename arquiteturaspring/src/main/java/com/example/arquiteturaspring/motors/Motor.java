package com.example.arquiteturaspring.motors;

public class Motor {


    private String model;
    private Integer horsePower;
    private Integer cylinder;
    private Double liquid;
    private MotorType motorType;


    public String getModel() {
        return model;
    }

    public void setModelo(String model) {
        this.model = model;
    }

    public MotorType getMotorType() {
        return motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    public Double getLiquid() {
        return liquid;
    }

    public void setLiquid(Double liquid) {
        this.liquid = liquid;
    }

    public Integer getCylinder() {
        return cylinder;
    }

    public void setCylinder(Integer cylinder) {
        this.cylinder = cylinder;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }


    @Override
    public String toString() {
        return "Motor{" +
                "model='" + model + '\'' +
                ", horsePower=" + horsePower +
                ", cylinder=" + cylinder +
                ", liquid=" + liquid +
                ", motorType=" + motorType +
                '}';
    }
}
